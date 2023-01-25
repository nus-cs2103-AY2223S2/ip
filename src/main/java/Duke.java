import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> allTasks = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    private static void printLine() {
        System.out.println("----------------------------------------------------");
    }

    private static void addToList(String title, TaskType type, LocalDateTime start, LocalDateTime end) {
        Task task;
        if (type == TaskType.TODO) {
            task = new ToDo(title);
        } else if (type == TaskType.DEADLINE) {
            task = new Deadline(title, end);
        } else if (type == TaskType.EVENT) {
            task = new Event(title, start, end);
        } else {
            System.out.println("Something seems wrong...");
            return;
        }
        allTasks.add(task);
        System.out.println("Added this to your task list:");
        System.out.println("  " + task.toString());
        System.out.println(String.format("Number of tasks left: %d", allTasks.size()));
    }

    private static void deleteTask(int taskIndex) {
        Task deletedTask = allTasks.remove(taskIndex);
        System.out.println("Removed this from your task list:");
        System.out.println("  " + deletedTask.toString());
        System.out.println(String.format("Number of tasks left: %d", allTasks.size()));
    }

    private static void printList() {
        if (allTasks.size() == 0) {
            System.out.println("You have zero tasks now!");
            return;
        }
        System.out.println("Your tasks so far!!");
        for (int i = 0; i < allTasks.size(); i++) {
            Task task = allTasks.get(i);
            String toPrint = String.format("%d. %s", i + 1, task.toString());
            System.out.println(toPrint);
        }
    }

    private static void changeTaskCompletionStatus(int taskNumber, boolean completionStatus) {
        Task task = allTasks.get(taskNumber);
        task.setDone(completionStatus);
        if (completionStatus) {
            System.out.println("Solid work man! This task is marked done");
        } else {
            System.out.println("Aww what happened? This task is marked as undone");
        }
        String toPrint = task.toString();
        System.out.println(toPrint);
    }

    private static boolean handleCommands(String rawCommand) throws DukeException {
        int commandIndex = rawCommand.indexOf(' ');
        String command = rawCommand;
        String arguments = "";
        if (commandIndex != -1) {
            // There is a space character in the command
            command = rawCommand.substring(0, commandIndex);
            arguments = rawCommand.substring(commandIndex + 1).trim();
        }

        switch (command) {
        case "bye":
            System.out.println("Sad...Alright bye!");
            return false;
        case "list":
            printList();
            break;
        case "mark":
            try {
                int markIndex = Integer.parseInt(arguments) - 1;
                changeTaskCompletionStatus(markIndex, true);
            } catch (Throwable e) {
                throw new IlegalCommandException(Commands.MARK);
            }
            break;
        case "unmark":
            try {
                int unmarkIndex = Integer.parseInt(arguments) - 1;
                changeTaskCompletionStatus(unmarkIndex, false);
            } catch (Throwable e) {
                throw new IlegalCommandException(Commands.UNMARK);
            }
            break;
        case "todo":
            if (arguments.trim().equals("")) {
                throw new IlegalCommandException(Commands.TODO);
            }
            addToList(arguments, TaskType.TODO, null, null);
            break;
        case "deadline":
            try {
                int slashIndex = arguments.indexOf('/');
                String dateBy = arguments.substring(slashIndex + 4);
                addToList(arguments.substring(0, slashIndex - 1), TaskType.DEADLINE, null, dateBy);
            } catch (Throwable e) {
                throw new IlegalCommandException(Commands.DEADLINE);
            }
            break;
        case "event":
            try {
                int firstSlashIndex = arguments.indexOf('/');
                String startAndEnd = arguments.substring(firstSlashIndex + 6);
                int secondSlashIndex = startAndEnd.indexOf('/');
                String start = startAndEnd.substring(0, secondSlashIndex - 1);
                String end = startAndEnd.substring(secondSlashIndex + 4);
                addToList(arguments.substring(0, firstSlashIndex - 1), TaskType.EVENT, start, end);
            } catch (Throwable e) {
                throw new IlegalCommandException(Commands.EVENT);
            }
            break;
        case "delete":
            try {
                int deleteIndex = Integer.parseInt(arguments) - 1;
                deleteTask(deleteIndex);
            } catch (Throwable e) {
                throw new IlegalCommandException(Commands.DELETE);
            }
            break;
        default:
            throw new IlegalCommandException(Commands.UNRECOGNIZED);
        }
        return true;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        printLine();
        System.out.println(logo);
        System.out.println("Hope you are doing great!");
        System.out.println("What can I do for you?");
        printLine();

        boolean promptAgain = true;
        while (promptAgain) {
            System.out.println("Enter your prompt below:");
            String command = sc.nextLine();
            try {
                promptAgain = handleCommands(command);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
            printLine();
        }
    }
}
