import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {
    protected static final String FILE_DIRECTORY = "../../../data";
    protected static final String FILE_PATH = "../../../data/duke.txt";
    protected static String indent = "     ";
    protected static String divider = indent + "____________________________________________________________";
    protected static ArrayList<Task> tasks =  new ArrayList<>(100);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printWelcomeMessage();

        try {
            loadTasks();
        } catch (FileNotFoundException e) {
            System.out.println("No save data found!");
        } catch (IOException | DukeException e) {
            System.out.println("Error loading save data");
        }

        while (true) {
            try {
                String[] command = sc.nextLine().split(" ", 2);
                if (command[0].equals("bye")) {
                    saveTasks();
                    System.out.println(formatMessage("Bye. Hope to see you again soon!"));
                    break;
                } else if (command[0].equals("list")) {
                    System.out.println(formatMessage(listTasks()));
                } else if (command[0].equals("mark")) {
                    if (command.length < 2) {
                        throw new DukeException("Task number required");
                    }
                    int taskNum = Integer.parseInt(command[1]) - 1;
                    if (taskNum < 0 || taskNum >= tasks.size()) {
                        throw new DukeException("Task number invalid");
                    }
                    tasks.get(taskNum).mark();
                    System.out.println(formatMessage("Nice! I've marked this task as done:\n" +
                            indent + tasks.get(taskNum).toString()));
                } else if (command[0].equals("unmark")) {
                    if (command.length < 2) {
                        throw new DukeException("Task number required");
                    }
                    int taskNum = Integer.parseInt(command[1]) - 1;
                    if (taskNum < 0 || taskNum >= tasks.size()) {
                        throw new DukeException("Task number invalid");
                    }
                    tasks.get(taskNum).unmark();
                    System.out.println(formatMessage("OK, I've marked this task as not done yet:\n" +
                            indent + tasks.get(taskNum).toString()));
                } else if (command[0].equals("delete")) {
                    if (command.length < 2) {
                        throw new DukeException("Task number required");
                    }
                    int taskNum = Integer.parseInt(command[1]) - 1;
                    if (taskNum < 0 || taskNum >= tasks.size()) {
                        throw new DukeException("Task number invalid");
                    }
                    String removedTask = tasks.get(taskNum).toString();
                    tasks.remove(taskNum);
                    System.out.println(formatMessage("Noted. I've removed this task:\n" +
                            indent + indent + removedTask + "\n" +
                            indent + "Now you have " + tasks.size() + " task(s) in the list."));
                } else {
                    if (command.length < 2) {
                        throw new DukeException("Invalid input");
                    }
                    switch (command[0]) {
                        case "todo":
                            tasks.add(new Todo(command[1]));
                            break;
                        case "deadline": {
                            String[] arguments = command[1].split(" /by ");
                            if (arguments.length < 2) {
                                throw new DukeException("Deadline needs a \"by date\"");
                            }
                            tasks.add(new Deadline(arguments[0], arguments[1]));
                            break;
                        }
                        case "event": {
                            String[] arguments = command[1].split(" /from ");
                            if (arguments.length < 2) {
                                throw new DukeException("invalid format");
                            }
                            String[] timings = arguments[1].split(" /to ");
                            if (timings.length < 2) {
                                throw new DukeException("invalid format");
                            }
                            tasks.add(new Event(arguments[0], timings[0], timings[1]));
                            break;
                        }
                        default:
                            throw new DukeException("I do not understand");
                    }

                    System.out.println(formatMessage("Got it. I've added this task:\n" +
                            indent + indent + tasks.get(tasks.size() - 1).toString() + "\n" +
                            indent + "Now you have " + tasks.size() + " task(s) in the list."));
                }
            } catch (DukeException e) {
                System.out.println(formatMessage(e.getMessage()));
            } catch (NumberFormatException e) {
                System.out.println("Valid task required");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void printWelcomeMessage() {
        String logo = indent + " ____        _        \n"
                + indent + "|  _ \\ _   _| | _____ \n"
                + indent + "| | | | | | | |/ / _ \\\n"
                + indent + "| |_| | |_| |   <  __/\n"
                + indent + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println(logo);
        System.out.println(divider);
        System.out.println(indent + "Hello! I'm Duke");
        System.out.println(indent + "What can I do for you?");
        System.out.println(divider);
    }

    public static String formatMessage(String message) {
        return divider + "\n" + indent + message + "\n" + divider;
    }

    public static String listTasks() {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            output += indent + (i + 1) + ". " + tasks.get(i).toString();
            if (i < tasks.size() - 1) {
                output += "\n";
            }
        }
        return output;
    }

    public static void saveTasks() throws IOException {
        File dir = new File(FILE_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File dataFile = new File(FILE_PATH);
        dataFile.createNewFile();

        FileWriter myWriter = new FileWriter(FILE_PATH);
        boolean isFirst = true;
        for (Task t : tasks) {
            if (!isFirst) {
                myWriter.write("\n");
            }
            myWriter.write(t.toSaveString());
            isFirst = false;
        }
        myWriter.close();
    }

    public static void loadTasks() throws IOException, DukeException {
        Scanner fileReader = new Scanner(new File(FILE_PATH));
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            String[] taskData = data.split("\\$\\$\\$");

            switch (taskData[0]) {
                case "T":
                    tasks.add(new Todo(taskData[1]));
                    break;
                case "D":
                    tasks.add(new Deadline(taskData[1], taskData[2]));
                    break;
                case "E":
                    tasks.add(new Event(taskData[1], taskData[2], taskData[3]));
                    break;
                default:
                    throw new DukeException("Error loading tasks from file!");
            }
        }

        fileReader.close();
    }
}
