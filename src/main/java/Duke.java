import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> taskList = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    private static void printLine() {
        System.out.println("----------------------------------------------------");
    }

    private static void addToList(String title, TaskType type) {
        // TODO: Handle all task types
        Task task;
        if (type == TaskType.TODO) {
            task = new ToDo(title);
        } else if (type == TaskType.DEADLINE) {
            task = new Deadline(title, "test");
        } else if (type == TaskType.EVENT) {
            task = new Event(title, "test", "test");
        } else {
            System.out.println("Something seems wrong...");
            return;
        }
        taskList.add(task);
        System.out.println(task.toString());
    }

    private static void printList() {
        System.out.println("Your tasks so far!!");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String completedSubstring = task.getDone() ? "[x]" : "[]";
            String toPrint = String.format("%d.%s %s", i + 1, completedSubstring, task.getTitle());
            System.out.println(toPrint);
        }
    }

    private static void changeTaskCompletionStatus(int taskNumber, boolean completionStatus) {
        Task task = taskList.get(taskNumber);
        task.setDone(completionStatus);
        String completedSubstring = completionStatus ? "[x]" : "[]";
        if (completionStatus) {
            System.out.println("Solid work man! This task is marked done");
        } else {
            System.out.println("Aww what happened? This task is marked as undone");
        }
        String toPrint = String.format("  %s %s", completedSubstring, task.getTitle());
        System.out.println(toPrint);
    }

    private static boolean commandHandler(String rawCommand) {
        int commandIndex = rawCommand.indexOf(' ');
        String command;
        String arguments;
        if (commandIndex != -1) {
            // There is no space character in the command
            command = rawCommand.substring(0, commandIndex);
            arguments = rawCommand.substring(commandIndex);
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
                changeTaskCompletionStatus(Integer.parseInt(arguments.substring(1)) - 1, true);
                break;
            case "unmark":
                changeTaskCompletionStatus(Integer.parseInt(arguments.substring(1)) - 1, false);
                break;
            case "todo":
                addToList(rawCommand, TaskType.TODO);
                break;
            case "deadline":
                addToList(rawCommand, TaskType.DEADLINE);
                break;
            case "event":
                addToList(rawCommand, TaskType.EVENT);
                break;
            default:
                System.out.println("Unrecognized Command");
                break;
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
            promptAgain = commandHandler(command);
            printLine();
        }
    }
}
