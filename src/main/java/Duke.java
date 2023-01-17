import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private static final String GREETING = "Hello! I'm Duke\n    What can I do for you?";
    private static final String GOODBYE = "Bye. Hope to see you again soon!";

    /**
     * Checks the user input for termination command.
     * @param inp The user input.
     * @return True if the user input is the termination command.
     */
    private static boolean checkEnd(String inp) {
        return Objects.equals(inp.toLowerCase(), "bye");
    }

    /**
     * Wraps the output in lines as shown in example
     * @param out The output message to terminal.
     * @return The wrapped output.
     */
    private static String outputWrapper(String out) {
        String topLine = "    ____________________________________________________________\n    ";
        String bottomLine = "\n    ____________________________________________________________";
        return topLine + out + bottomLine;

    }
    public static void main(String[] args) {
        System.out.println(outputWrapper(GREETING));
        Scanner input = new Scanner(System.in);

        while (true) {
            String userInput = input.nextLine();
            if (checkEnd(userInput)) {
                break;
            }
            System.out.println(outputWrapper(userInput));
        }

        System.out.println(outputWrapper(GOODBYE));
    }
}
