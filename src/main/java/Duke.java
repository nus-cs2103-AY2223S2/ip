import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {

    private static ArrayList<String> todoList = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    private static void printLine() {
        System.out.println("----------------------------------------------------");
    }

    private static void addToList(String task) {
        todoList.add(task);
        String toPrint = String.format("added: %s", task);
        System.out.println(toPrint);
    }

    private static boolean commandHandler(String command) {
//      TODO: Maybe remove this check here, and shift it to main loop
        if (Objects.equals(command, "bye")) {
            System.out.println("Sad...Alright bye!");
            return false;
        }
        if (Objects.equals(command, "list")) {
            // TODO: Print list
        } else {
            addToList(command);
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
