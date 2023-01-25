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
        TaskList taskList = new TaskList();
        
        printWelcomeMessage();

        try {
            storage.loadTasks(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("No save data found!");
        } catch (IOException | DukeException e) {
            System.out.println("Error loading save data");
        }

        while (true) {
            try {
                String[] command = sc.nextLine().split(" ", 2);
                if (command[0].equals("bye")) {
                    storage.saveTasks(taskList);
                    System.out.println(formatMessage("Bye. Hope to see you again soon!"));
                    break;
                } else if (command[0].equals("list")) {
                    System.out.println(formatMessage(taskList.listTasks()));
                } else if (command[0].equals("mark")) {
                    if (command.length < 2) {
                        throw new DukeException("Task number required");
                    }
                    int taskNum = Integer.parseInt(command[1]) - 1;
                    taskList.markTask(taskNum);
                    System.out.println(formatMessage("Nice! I've marked this task as done:\n" +
                            indent + taskList.getTasks().get(taskNum).toString()));
                } else if (command[0].equals("unmark")) {
                    if (command.length < 2) {
                        throw new DukeException("Task number required");
                    }
                    int taskNum = Integer.parseInt(command[1]) - 1;
                    taskList.unmarkTask(taskNum);
                    System.out.println(formatMessage("OK, I've marked this task as not done yet:\n" +
                            indent + taskList.getTasks().get(taskNum).toString()));
                } else if (command[0].equals("delete")) {
                    if (command.length < 2) {
                        throw new DukeException("Task number required");
                    }
                    int taskNum = Integer.parseInt(command[1]) - 1;
                    String removedTask = taskList.deleteTask(taskNum);
                    System.out.println(formatMessage("Noted. I've removed this task:\n" +
                            indent + indent + removedTask + "\n" +
                            indent + "Now you have " + taskList.getTasks().size() + " task(s) in the list."));
                } else {
                    if (command.length < 2) {
                        throw new DukeException("Invalid input");
                    }
                    switch (command[0]) {
                        case "todo":
                            taskList.getTasks().add(new Todo(command[1]));
                            break;
                        case "deadline": {
                            String[] arguments = command[1].split(" /by ");
                            if (arguments.length < 2) {
                                throw new DukeException("Deadline needs a \"by date\"");
                            }
                            taskList.getTasks().add(new Deadline(arguments[0], arguments[1]));
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
                            taskList.getTasks().add(new Event(arguments[0], timings[0], timings[1]));
                            break;
                        }
                        default:
                            throw new DukeException("I do not understand");
                    }

                    System.out.println(formatMessage("Got it. I've added this task:\n" +
                            indent + indent + taskList.getTasks().get(taskList.getTasks().size() - 1).toString() + "\n" +
                            indent + "Now you have " + taskList.getTasks().size() + " task(s) in the list."));
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

}
