import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {
    protected static final String FILE_DIRECTORY = "../../../data";
    protected static final String FILE_PATH = "../../../data/duke.txt";
    protected static String indent = "     ";
    protected static String divider = indent + "____________________________________________________________";


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage(FILE_DIRECTORY, FILE_PATH);

        printWelcomeMessage();

        try {
            storage.loadTasks();
        } catch (FileNotFoundException e) {
            System.out.println("No save data found!");
        } catch (IOException | DukeException e) {
            System.out.println("Error loading save data");
        }

        while (true) {
            try {
                String[] command = sc.nextLine().split(" ", 2);
                if (command[0].equals("bye")) {
                    storage.saveTasks();
                    System.out.println(formatMessage("Bye. Hope to see you again soon!"));
                    break;
                } else if (command[0].equals("list")) {
                    System.out.println(formatMessage(listTasks()));
                } else if (command[0].equals("mark")) {
                    if (command.length < 2) {
                        throw new DukeException("Task number required");
                    }
                    int taskNum = Integer.parseInt(command[1]) - 1;
                    if (taskNum < 0 || taskNum >= Task.tasks.size()) {
                        throw new DukeException("Task number invalid");
                    }
                    Task.tasks.get(taskNum).mark();
                    System.out.println(formatMessage("Nice! I've marked this task as done:\n" +
                            indent + Task.tasks.get(taskNum).toString()));
                } else if (command[0].equals("unmark")) {
                    if (command.length < 2) {
                        throw new DukeException("Task number required");
                    }
                    int taskNum = Integer.parseInt(command[1]) - 1;
                    if (taskNum < 0 || taskNum >= Task.tasks.size()) {
                        throw new DukeException("Task number invalid");
                    }
                    Task.tasks.get(taskNum).unmark();
                    System.out.println(formatMessage("OK, I've marked this task as not done yet:\n" +
                            indent + Task.tasks.get(taskNum).toString()));
                } else if (command[0].equals("delete")) {
                    if (command.length < 2) {
                        throw new DukeException("Task number required");
                    }
                    int taskNum = Integer.parseInt(command[1]) - 1;
                    if (taskNum < 0 || taskNum >= Task.tasks.size()) {
                        throw new DukeException("Task number invalid");
                    }
                    String removedTask = Task.tasks.get(taskNum).toString();
                    Task.tasks.remove(taskNum);
                    System.out.println(formatMessage("Noted. I've removed this task:\n" +
                            indent + indent + removedTask + "\n" +
                            indent + "Now you have " + Task.tasks.size() + " task(s) in the list."));
                } else {
                    if (command.length < 2) {
                        throw new DukeException("Invalid input");
                    }
                    switch (command[0]) {
                        case "todo":
                            Task.tasks.add(new Todo(command[1]));
                            break;
                        case "deadline": {
                            String[] arguments = command[1].split(" /by ");
                            if (arguments.length < 2) {
                                throw new DukeException("Deadline needs a \"by date\"");
                            }
                            Task.tasks.add(new Deadline(arguments[0], arguments[1]));
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
                            Task.tasks.add(new Event(arguments[0], timings[0], timings[1]));
                            break;
                        }
                        default:
                            throw new DukeException("I do not understand");
                    }

                    System.out.println(formatMessage("Got it. I've added this task:\n" +
                            indent + indent + Task.tasks.get(Task.tasks.size() - 1).toString() + "\n" +
                            indent + "Now you have " + Task.tasks.size() + " task(s) in the list."));
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
        for (int i = 0; i < Task.tasks.size(); i++) {
            output += indent + (i + 1) + ". " + Task.tasks.get(i).toString();
            if (i < Task.tasks.size() - 1) {
                output += "\n";
            }
        }
        return output;
    }
}
