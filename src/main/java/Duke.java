import java.util.Scanner;

public class Duke {

    private static void displayMessage(String message) {
        System.out.println(
                "-----------------------------------------------------------\n" +
                message + "\n" +
                "-----------------------------------------------------------\n"
        );
    }

    private void init() {
        displayMessage("Hello! I'm Bob\n" +
                "What can I do for you?");
    }

    private void exit() {
        displayMessage("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.init();
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            if (input.equalsIgnoreCase("bye")) {
                duke.exit();
                break;
            }
            displayMessage(input);
        }
    }
}
