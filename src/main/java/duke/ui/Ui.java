package duke.ui;

import java.io.PrintStream;

/**
 * Handles displaying of information to the user.
 */
public class Ui {
    private final PrintStream printStream;

    /**
     * Creates a Ui object.
     *
     * @param printStream The PrintStream object to print output messages to.
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
        printStream.println("    ____________________________________________________________");
        printStream.printf("     %s\n", message.replace("\n", "\n     "));
        printStream.print("    ____________________________________________________________\n\n");
    }
}
