package duke;

/**
 * Customised how messages are printed to console.
 */
public class Ui {

    /** Name of the program developer. */
    private static final String AUTHOR = "lhy-hoyin";

    /** Printable of the program name. */
    private static final String LOGO
            = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";

    /**
     * Constructs an instance of Ui.
     */
    public Ui() {
        // Empty
    }

    /**
     * Prints specified message to console and move cursor to next line.
     * Similar to System.out.println.
     *
     * @param message Details of message to be printed.
     */
    public void println(String message) {
        System.out.println(message);
    }

    /**
     * Prints specified warning to console and move cursor to next line.
     * Message is prepended with a "OOPS!" to indicate that something unexpected
     * has happened.
     *
     * @param message Details of warning to be printed.
     */
    public void warn(String message) {
        System.out.println("OOPS! " + message);
    }

    /**
     * Prints the program logo and developer name.
     * Static context allows this to be printed even more an instance has been initialized.
     */
    static void printProgramInfo() {
        System.out.println(LOGO);
        System.out.println("Developed by: " + AUTHOR);
    }

    /**
     * Prints a buffer line.
     */
    void printBufferLine() {

        println("____________________________________________________________");
    }
}
