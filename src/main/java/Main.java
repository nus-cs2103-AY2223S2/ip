import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Duke duke = new Duke();

        // greeting
        duke.print_structured_string(duke.greeting());

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
                duke.handleCommand(inMsg);
            } catch (DukeException e) {
                duke.print_structured_string(e.toString());
            } catch (NumberFormatException e) {
                duke.print_structured_string("Please enter a number.");
            }
        }

        try {
            // bye-bye message
            duke.print_structured_string(duke.endMsg());
        } catch (DukeException e) {
            duke.print_structured_string(e.toString());
        }
    }
}
