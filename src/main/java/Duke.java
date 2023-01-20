import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> taskList = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    private static void printLine() {
        System.out.println("----------------------------------------------------");
    }

    private static void addToList(String title, TaskType type, String start, String end) {
        // TODO: Handle all task types
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
        taskList.add(task);
        System.out.println("Added this to your task list:");
        System.out.println("  " + task.toString());
        System.out.println(String.format("Number of tasks: %d", taskList.size()));
    }

    private static void printList() {
        if (taskList.size() == 0) {
            System.out.println("You have zero tasks now!");
            return;
        }
        System.out.println("Your tasks so far!!");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String toPrint = String.format("%d. %s", i + 1, task.toString());
            System.out.println(toPrint);
        }
    }

    private static void changeTaskCompletionStatus(int taskNumber, boolean completionStatus) {
        Task task = taskList.get(taskNumber);
        task.setDone(completionStatus);
        if (completionStatus) {
            System.out.println("Solid work man! This task is marked done");
        } else {
            System.out.println("Aww what happened? This task is marked as undone");
        }
        String toPrint = task.toString();
        System.out.println(toPrint);
    }

    private static boolean commandHandler(String rawCommand) throws DukeException {
        int commandIndex = rawCommand.indexOf(' ');
        String command;
        String arguments;
        if (commandIndex != -1) {
            // There is no space character in the command
            command = rawCommand.substring(0, commandIndex);
            arguments = rawCommand.substring(commandIndex + 1);
        } else {
            command = rawCommand;
            arguments = null;
        }

        switch (command) {
            case "bye":
                System.out.println("Sad...Alright bye!");
                return false;
            case "list":
                printList();
                break;
            case "mark":
                changeTaskCompletionStatus(Integer.parseInt(arguments) - 1, true);
                break;
            case "unmark":
                changeTaskCompletionStatus(Integer.parseInt(arguments) - 1, false);
                break;
            case "todo":
                addToList(arguments, TaskType.TODO, null, null);
                break;
            case "deadline":
                int slashIndex = arguments.indexOf('/');
                String dateBy = arguments.substring(slashIndex + 4);
                addToList(arguments.substring(0, slashIndex - 1), TaskType.DEADLINE, null, dateBy);
                break;
            case "event":
                int firstSlashIndex = arguments.indexOf('/');
                String startAndEnd = arguments.substring(firstSlashIndex + 6);
                int secondSlashIndex = startAndEnd.indexOf('/');
                String start = startAndEnd.substring(0, secondSlashIndex - 1);
                String end = startAndEnd.substring(secondSlashIndex + 4);
                addToList(arguments.substring(0, firstSlashIndex - 1), TaskType.EVENT, start, end);
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
                promptAgain = commandHandler(command);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
            printLine();
        }
    }
}
