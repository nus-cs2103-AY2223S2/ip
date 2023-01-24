package duke.ui;

import java.io.PrintStream;

/**
 * Handles displaying of messages to the user.
 */
public class Ui {
    private final PrintStream printStream;

    /**
     * Creates a Ui object.
     *
     * @param printStream The PrintStream object to print messages to.
     */
    public Ui(PrintStream printStream) {
        this.printStream = printStream;
    }

    /**
     * Prints a specified message.
     *
     * @param message The message to be printed.
     */
    public void print(String message) {
        printStream.println("    ______________________________________________________________________");
        printStream.printf("     %s\n", message.replace("\n", "\n     "));
        printStream.println("    ______________________________________________________________________\n");
    }
}
