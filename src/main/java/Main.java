import java.util.Scanner;

/**
 * The main class
 */
public class Main {
    public static void main(String[] args) {
        Duke duke = new Duke();

        // greeting
        duke.printStructuredString(duke.greeting());

        // read input
        Scanner scanner = new Scanner(System.in);

        // main loop
        String inMsg = null;
        while (true) {
            inMsg = scanner.nextLine();
            if (duke.isEnd(inMsg)) {
                break;
            }
            try {
                duke.handleCommand(inMsg, false);
                duke.addCommandList(inMsg);
            } catch (DukeException e) {
                duke.printStructuredString(e.toString());
            } catch (NumberFormatException e) {
                duke.printStructuredString("Please enter a number.");
            }
        }

        try {
            // bye-bye message
            duke.printStructuredString(duke.endMsg());
        } catch (DukeException e) {
            duke.printStructuredString(e.toString());
        }
    }
}
