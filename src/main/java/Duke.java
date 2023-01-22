/**
 * Represents Duke itself, the Chat bot.
 */

import java.util.Scanner;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
public class Duke {

    private TaskList tasks;
    private final Storage storage;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Saves the current TaskList into a specified file.
     * @param taskList List of Tasks to be saved.
     * @throws IOException If specified file does not exist.
     */
    public void saveTaskList(List<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(storage.getFilePath());
        for (Task a : taskList) {
            fw.write(a.getDataToSave() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Returns the command (e.g. "bye", "mark", "event", etc.).
     * @param str User input.
     * @return Command.
     */
    public String getCommand(String str) {
        String[] split = str.split(" ", 2);
        return split[0];
    }

    /**
     * Returns details (e.g. the index for a "mark" command, or the description for an "event" command).
     * @param str User input.
     * @return Command details.
     */
    public String getCommandDetails(String str) {
        String[] split = str.split(" ", 2);
        return split[1];
    }

    /**
     * Returns description of a Deadline task.
     * @param str Command details.
     * @return Description of a deadline task.
     */
    public String getDeadlineDesc(String str) {
        String[] split = str.split(" /by ");
        return split[0];
    }

    /**
     * Returns deadline of a Deadline task.
     * @param str Command details.
     * @return Deadline of a deadline task.
     */
    public String getDeadline(String str) {
        String[] split = str.split(" /by ");
        return split[1];
    }

    /**
     * Returns description of an Event task.
     * @param str Command details.
     * @return Description of an Event task.
     */
    public String getEventDesc(String str) {
        String[] split = str.split(" /from ");
        return split[0];
    }

    /**
     * Returns "from" timeframe of an Event task.
     * @param str Command details.
     * @return "From" timeframe of an Event task.
     */
    public String getEventFrom(String str) {
        String[] split = str.split(" /from ");
        return split[1].split(" /to ")[0];
    }

    /**
     * Returns "to" timeframe of an Event task.
     * @param str Command details.
     * @return "To" timeframe of an Event task.
     */
    public String getEventTo(String str) {
        String[] split = str.split(" /from ");
        return split[1].split(" /to ")[1];
    }

    /**
     * Prints the tasks in the TaskList upon command "list".
     */
    public void printTaskList() {
        int num = 1;
        System.out.println("---");
        for (Task a : tasks.getTasks()) {
            System.out.println(num + ". " + a);
            num++;
        }
        if (num == 1) {
            System.out.println("there are no items in your task list");
        }
        System.out.println("---");
    }

    /**
     * Duke takes in user input and addresses it accordingly.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("---\n hi i'm Duke! what's up? \n---");

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            String command = getCommand(userInput);
            int index;
            switch (command) {
            case "mark":
                index = Integer.parseInt(getCommandDetails(userInput)) - 1;
                tasks = tasks.set(index, tasks.get(index).markAsDone());
                System.out.println("---\nnice! i've marked this task as done:\n"
                        + tasks.get(index) + "\n---");
                break;
            case "unmark":
                index = Integer.parseInt(getCommandDetails(userInput)) - 1;
                tasks = tasks.set(index, tasks.get(index).unmarkAsDone());
                System.out.println("---\nok, i've marked this task as not done yet:\n"
                        + tasks.get(index) + "\n---");
                break;
            case "bye":
                System.out.println("---\nbye! see u soon! :-)\n---");
                System.exit(0);
                break;
            case "list":
                printTaskList();
                break;
            case "todo":
                try {
                    Todo newTodo = new Todo(getCommandDetails(userInput));
                    tasks = tasks.add(newTodo);
                    System.out.println("---\ni've added this task:\n" + newTodo);
                    if (tasks.size() == 1) {
                        System.out.println("you have 1 task in the list\n---");
                    } else {
                        System.out.println("you have " + tasks.size() + " tasks in the list\n---");
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("---\n"
                            + new DukeException("pls add a description for this task!") + "\n---");
                }
                break;
            case "deadline":
                try {
                    Deadline newDeadline = new Deadline(getDeadlineDesc(getCommandDetails(userInput)),
                            getDeadline(getCommandDetails(userInput)));
                    tasks = tasks.add(newDeadline);
                    System.out.println("---\ni've added this task with deadline:\n" + newDeadline);
                    if (tasks.size() == 1) {
                        System.out.println("you have 1 task in the list\n---");
                    } else {
                        System.out.println("you have " + tasks.size() + " tasks in the list\n---");
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("---\n"
                            + new DukeException("pls specify the description and deadline for this task!")
                            + "\n---");
                }
                break;
            case "event":
                try {
                    Event newEvent = new Event(getEventDesc(getCommandDetails(userInput)),
                            getEventFrom(getCommandDetails(userInput)), getEventTo(getCommandDetails(userInput)));
                    tasks = tasks.add(newEvent);
                    System.out.println("---\ni've added this event:\n" + newEvent);
                    if (tasks.size() == 1) {
                        System.out.println("you have 1 task in the list\n---");
                    } else {
                        System.out.println("you have " + tasks.size() + " tasks in the list\n---");
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("---\n"
                            + new DukeException("pls specify the description and duration for this event!")
                            + "\n---");
                }
                break;
            case "delete":
                index = Integer.parseInt(getCommandDetails(userInput)) - 1;
                System.out.println("---\ni've removed this task:\n" + tasks.get(index));
                tasks = tasks.remove(index);
                if (tasks.size() == 1) {
                    System.out.println("you have 1 task in the list\n---");
                } else {
                    System.out.println("you have " + tasks.size() + " tasks in the list\n---");
                }
                break;
            default:
                System.out.println("---\n" + new DukeException("invalid command") + "\n---");
            }
            try {
                saveTaskList(tasks.getTasks());
            } catch (IOException e) {
                System.out.println("Something went wrong!");
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}