package duke;

/**
 * Customise how messages are printed to console.
 * Allow messages to be printed elsewhere (eg. GUI) in batches.
 */
public class Ui {

    /** Name of the program developer. */
    private static final String AUTHOR = "lhy-hoyin";

    /** Printable of the program name. */
    private static final String LOGO = ""
            + " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";

    private StringBuilder printCache = new StringBuilder();

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
        printCache.append(message).append("\n");
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
        printCache.append("OOPS! ").append(message).append("\n");
        System.out.println("OOPS! " + message);
    }

    /**
     * Returns a String of this print cache (messages printed to console) by the Ui
     * @return String of this print cache
     */
    public String getRecentMessages() {
        String recentMessage = printCache.toString();
        printCache = new StringBuilder(); // reset cache
        return recentMessage;
    }

    /**
     * Prints a buffer line.
     */
    void printBufferLine() {
        println("____________________________________________________________");
    }

    /**
     * Prints the program logo and developer name.
     * Static context allows this to be printed even more an instance has been initialized.
     */
    static void printProgramInfo() {
        System.out.println(LOGO);
        System.out.println("Developed by: " + AUTHOR);
    }

}
