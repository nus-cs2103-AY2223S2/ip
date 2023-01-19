import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> todoList = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    private static void printLine() {
        System.out.println("----------------------------------------------------");
    }

    private static void addToList(String title) {
        Task task = new Task(title);
        todoList.add(task);
        String toPrint = String.format("added: %s", task.getTitle());
        System.out.println(toPrint);
    }

    private static void printList() {
        System.out.println("Your tasks so far!!");
        for (int i = 0; i < todoList.size(); i++) {
            Task task = todoList.get(i);
            String completedSubstring = task.getDone() ? "[x]" : "[]";
            String toPrint = String.format("%d.%s %s", i + 1, completedSubstring, task.getTitle());
            System.out.println(toPrint);
        }
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
            default:
                addToList(rawCommand);
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
