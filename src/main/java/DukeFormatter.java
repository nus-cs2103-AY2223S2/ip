import java.util.Arrays;

/**
 * Main Formatter class to format the UI for the Duke chat-bot in the terminal.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
abstract public class DukeFormatter {
    private static String MASCOT = "Rick: ";
    private static String INDENT = "      ";
    private static String LINE =
            "____________________________________________________________";

    private static void line() {
        System.out.println(INDENT + LINE);
    }

    /**
     * Main Formatting function to format any UI returned to the user after a
     * command. Prints out the UI to the console for viewing by the user.
     *
     * @param inputs The UI to be formatted
     */
    public static void section(String inputs) {
        //Start
        line();
        String[] lines = inputs.split("\n");
        System.out.println(MASCOT + lines[0]);
        for (String line: Arrays.copyOfRange(lines, 1, lines.length)) {
            System.out.println(INDENT + line);
        }
        //End
        line();
        System.out.print("\n");
    }

    /**
     * Given an exception that occurred, formats the UI to indicate the error
     * to the user.
     *
     * @param error The error that occurred.
     */
    public static void error(Exception error) {
        line();
        System.out.println(MASCOT + error.getMessage());
        line();
        System.out.print("\n");
    }

    /**
     * Produces a user guide for incorrect command usages.
     */
    public static void guide(String message) {
        line();
        System.out.println(MASCOT + message);
        line();
        System.out.print("\n");
    }
}
