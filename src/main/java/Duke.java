import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class Duke implements a chatbot encapsulates user's tasks and show it
 * to the user later by processing the inputs.
 *
 * @author hhchinh2002
 */
public class Duke {
    private static final String DIVIDER_LINE = "____________________________________________________\n";
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static final Path tasksFilePath = Paths.get(".", ".", ".", "data", "duke.txt");
    private static final Path tempTasksFilePath = Paths.get(".", ".", ".", "data", "temp_duke.txt");

    private static void start() {
        System.out.println(reply("Hello! I'm Duke\n What can I do for you?" + "\n"));
        try {
            Files.createFile(tasksFilePath);
        } catch (FileAlreadyExistsException e) {
            readFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayOutro() {
        System.out.println(reply("Bye. Hope to see you again soon!" + "\n"));
    }

    private static void readFile() {
        Scanner reader = null;
        try {
            reader = new Scanner(tasksFilePath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (reader != null) {
            String pointer;
            String description;
            Task newTask;
            while (reader.hasNext()) {
                pointer = reader.nextLine();

                //checks which kinds of task is stored in this current line
                if (pointer.startsWith("[T]")) {
                    description = pointer.substring(7);
                    newTask = new ToDo(description);
                } else if (pointer.startsWith("[E]")) {
                    description = pointer.substring(7, pointer.indexOf("(from: ") - 1);
                    LocalDate start = LocalDate.parse(
                            pointer.substring(pointer.indexOf("(from: ") + 7, pointer.indexOf(" to:")),
                            DateTimeFormatter.ofPattern("MMM d yyyy"));
                    LocalDate end = LocalDate.parse(
                            pointer.substring(pointer.indexOf("to: ") + 4, pointer.lastIndexOf(")")),
                            DateTimeFormatter.ofPattern("MMM d yyyy"));
                    newTask = new Event(description, start, end);
                } else {
                    description = pointer.substring(7, pointer.indexOf("(by: ") - 1);
                    LocalDate by = LocalDate.parse(
                            pointer.substring(pointer.indexOf("(by: ") + 5, pointer.lastIndexOf(")")),
                            DateTimeFormatter.ofPattern("MMM d yyyy"));
                    newTask = new Deadline(description, by);
                }

                tasks.add(newTask);
                //checks whether this tasks is marked as done or not
                if (pointer.substring(3).startsWith("[X]")) {
                    newTask.mark();
                }
            }
            reader.close();
        }
    }

    private static void writeFile() {
        Scanner reader = null;
        String text = "";
        try {
            Files.deleteIfExists(tempTasksFilePath);
            Files.createFile(tempTasksFilePath);
            for (int i = 0; i < tasks.size(); i++) {
                text += tasks.get(i) + "\n";
            }
            Files.writeString(tempTasksFilePath, text);
            Files.deleteIfExists(tasksFilePath);
            Files.move(tempTasksFilePath, tasksFilePath);
        } catch (IOException e) {
            System.out.print(e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    private static void addTask(Task newTask) {
        tasks.add(newTask);
        writeFile();
        System.out.println(reply("Got it. I've added this task:\n  "
                    + newTask
                    + "\nNow you have " + tasks.size() + " tasks in the list\n"));

    }

    private static void mark(String command) {
        int index = Integer.valueOf(command.substring(5)) - 1;
        Task target = tasks.get(index);
        target.mark();
        writeFile();
        System.out.println(reply("Nice! I've marked this task as above:\n  " + target + "\n"));
    }

    private static void unmark(String command) {
        int index = Integer.valueOf(command.substring(7)) - 1;
        Task target = tasks.get(index);
        target.unmark();
        writeFile();
        System.out.println(reply("OK, I've marked this task as not done yet:\n  " + target + "\n"));
    }

    private static void displayTasks() {
        String tasksList = "";
        Task currentTask;
        for (int i = 0; i < tasks.size(); i++) {
            currentTask = tasks.get(i);
            tasksList += i + 1 + "." + currentTask.toString() + "\n";
        }
        System.out.println(reply("Here are the tasks in your list:\n" + tasksList));
    }

    private static void delete(String command) {
        int index = Integer.valueOf(command.substring(7)) - 1;
        Task target = tasks.get(index);
        System.out.println(reply("Noted. I've removed this task:\n"
                + target + "\nNow you have " + tasks.size() + "in the list\n"));
        tasks.remove(index);
        writeFile();
    }

    private static void handleTaskInput(Scanner input) {
        String command = input.nextLine();
        while (true) {
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                displayTasks();
                command = input.nextLine();
                continue;
            }

            if (command.startsWith("mark ")) {
                mark(command);
                command = input.nextLine();
                continue;
            } else if (command.startsWith("unmark ")) {
                unmark(command);
                command = input.nextLine();
                continue;
            } else if (command.startsWith("delete ")) {
                delete(command);
                command = input.nextLine();
                continue;
            }

            String description = command;
            Task newTask = new Task(command);
            try {
                if (command.startsWith("todo")) {
                    if (description.length() <= 5) {
                        throw new DukeException(reply("The description of a todo cannot be empty.\n"));
                    }
                    newTask = new ToDo(description.substring(5));
                } else if (command.startsWith("deadline")) {
                    if (description.length() <= 9) {
                        throw new DukeException(reply("The description of a deadline cannot be empty.\n"));
                    }
                    LocalDate by = LocalDate.parse(description.substring(description.indexOf(" /by ") + 5),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    description = description.substring(9, description.indexOf(" /by "));
                    newTask = new Deadline(description, by);
                } else if (command.startsWith("event")) {
                    if (description.length() <= 6) {
                        throw new DukeException(reply("The description of a event cannot be empty.\n"));
                    }
                    LocalDate start = LocalDate.parse(description.substring(description.indexOf(" /from ") + 7,
                            description.indexOf(" /to ")), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalDate end = LocalDate.parse(description.substring(description.indexOf(" /to ") + 5),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    description = description.substring(6, description.indexOf(" /from "));
                    newTask = new Event(description, start, end);
                } else {
                    throw new DukeException(reply("I'm sorry, but I don't know what that means :-(\n"));
                }
                if (!tasks.contains(newTask)) {
                    addTask(newTask);
                }
            } catch (DukeException e) {
                System.out.println(e);
            } finally {
                command = input.nextLine();
            }
        }
    }

    private static String reply(String command) {
        return DIVIDER_LINE + command + DIVIDER_LINE;
    }

    public static void main(String[] args) {
        start();
        Scanner input = new Scanner(System.in);
        handleTaskInput(input);
        displayOutro();
        input.close();
    }
}
