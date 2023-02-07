package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

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

    /**
     * Represents the Ui shown to user through the system. Default Constructor.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructs Ui
     * @param in input
     * @param out output
     */
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

    public String showWelcome() {
        return showToUser(GREETING);
    }

    public String showError(String errorMessage) {
        return showToUser(errorMessage);
    }

    public String showLoadingError() {
        return showToUser(LOADING_ERROR);
    }

    /**
     * Calls Ui to show an indexed list of tasks
     * @param tasks task list
     */
    public String showIndexedList(ArrayList<Task> tasks) {
        String[] output = new String[tasks.size()];
        int count = 1;
        for (Task t : tasks) {
            output[count-1] = (count + ". " + t.toString());
            count++;
        }
        return showToUser(output);
    }

    /** Shows a variable number of messages to user */
    public String showToUser(String... message) {
        StringBuilder output = new StringBuilder();
        for (String m : message) {
            output.append(m).append('\n');
        }
        return output.toString();
    }

}
