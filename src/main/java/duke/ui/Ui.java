package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * UI for Duke
 */
public class Ui {
    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();

    /** Simple greeting message */
    private static final String GREETING = "Welcome to Duke. How may I help you?";

    /** */
    private static final String LOADING_ERROR = "Failed to find existing duke.task.Task list. Creating new list...";

    /** Simple exit message */
    private static final String GOODBYE = "Bye! See you again.";

    /** Visible line break */
    private static final String DIVIDER = "____________________________________________________________";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Prompts user to enter command and reads the text entered by the user.
     * Echoes command back to user.
     * @return command (full line) entered by user
     */
    public String readCommand() {
        out.print("Enter command: ");
        String userInput = in.nextLine();

        showToUser("You entered: " + userInput);
        return userInput;
    }

    public void showWelcome() {
        showToUser(GREETING, DIVIDER);
    }

    public void showGoodBye() {
        showToUser(GOODBYE, DIVIDER);
    }

    public void showError(String errorMessage) {
        showToUser(errorMessage, DIVIDER);
    }

    public void showLine() {
        showToUser(DIVIDER);
    }

    public void showLoadingError() {
        showToUser(LOADING_ERROR);
    }

    /** Shows a variable number of messages to user */
    public void showToUser(String... message) {
        for (String m : message) {
            this.out.println(m.replace("\n", LS));
        }
    }

}
