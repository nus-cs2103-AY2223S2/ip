package duke.ui;

/**
 * This class manages the displaying of output to the user.
 */
public class Ui {

    private static final String LINE =
            "\t____________________________________________________________";
    private static final String LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Prints a greeting message to the user on the standard output.
     */
    public void greet() {
        System.out.println("Hello from\n"
                + LOGO
                + "\nWhat can I do for you?");
    }

    /**
     * Prints the given text on to the standard output. A line is
     * added before and after the text for aesthetic formatting.
     *
     * @param text The string to be printed.
     */
    public void printOutput(String text) {
        System.out.println(LINE);
        System.out.println("\t " + text);
        System.out.println(LINE);
    }

    /**
     * Prints a line on the standard output.
     */
    public void printLine() {
        System.out.println(LINE);
    }
}
