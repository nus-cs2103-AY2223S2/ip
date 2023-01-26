package saturday.utilities;

/**
 * The Ui class is a utility class that contains methods for displaying information to the user.
 */
public class Ui {
    /**
     * Prints a divider line to the console.
     */
    public static void divider() {
        System.out.println("\t_______________________________________________________");
    }

    /**
     * Prints a newline to the console.
     */
    public static void newline() {
        System.out.println();
    }

    /**
     * Prints the given text to the console.
     *
     * @param text the text to be printed to the console
     */
    public static void output(String text) {
        System.out.println("\t" + text);
    }

    /**
     * Prints a greeting message to the console.
     */
    public static void greet() {
        Ui.divider();
        Ui.output("Hello! I'm saturday.Saturday\n\tWhat can I do for you?");
        Ui.divider();
        Ui.newline();
    }
}
