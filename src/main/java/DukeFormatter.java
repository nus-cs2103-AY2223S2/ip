import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Main Formatter class to format the UI for the Duke chatbot in the terminal.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
abstract public class DukeFormatter {
    private static String MASCOT = "\uD83D\uDE3A: ";
    private static String INDENT = "    ";
    private static String LINE = "____________________________________________________________";

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
        boolean firstLine = true;
        for (String line: lines) {
            System.out.println((firstLine? MASCOT : INDENT) + line);
            firstLine = false;
        }
        //End
        line();
        System.out.print("\n");
    }
}
