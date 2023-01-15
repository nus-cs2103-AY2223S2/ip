import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final ArrayList<String> userInputs;

    private Duke() {
        this.userInputs = new ArrayList<>();
    }

    private static void displayMessage(String message) {
        System.out.println(
                "-----------------------------------------------------------\n" +
                message +
                "-----------------------------------------------------------\n"
        );
    }

    private void init() {
        displayMessage("""
                Hello! I'm Bob
                What can I do for you?
                """);
    }

    private void exit() {
        displayMessage("Bye. Hope to see you again soon!\n");
    }

    private void displayItemList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= userInputs.size(); i++) {
            sb.append(i).append(". ").append(userInputs.get(i-1)).append("\n");
        }
        displayMessage(sb.toString());
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.init();
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            if (input.equals("bye")) {
                duke.exit();
                break;
            }

            if (input.equals("list")) {
                duke.displayItemList();
                continue;
            }

            duke.userInputs.add(input);
            displayMessage("added: " + input + "\n");

        }
    }
}
