import java.util.Objects;
import java.util.Scanner;

public class Duke {

    private static Scanner sc = new Scanner(System.in);

    public static void printLine() {
        System.out.println("----------------------------------------------------");
    }

    public static boolean echoOrQuit(String command) {
        if (Objects.equals(command, "bye")) {
            System.out.println("Sad...Alright bye!");
            return false;
        }
        System.out.println(command);
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
            promptAgain = echoOrQuit(command);
            printLine();
        }
    }
}
