package duke.ui;

import java.util.Scanner;

import duke.exceptions.DukeException;

/**
 * Represents the Ui responsible for the interaction with the user.
 * @author pzhengze
 */
public class Ui {
    /** Reference for Scanner object for reading user input in command line. */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructor for Ui object.
     */
    public Ui() {}

    /**
     * Returns greeting for user.
     * @return The geeting
     */
    public String greet() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Prints the message String that was provided.
     * @param s The message String.
     * @return The response
     */
    public String printResponse(String s) {
        return printText(s);
    }

    /**
     * Prints the message in the DukeException that was provided.
     * @param e The DukeException.
     * @return The exception message.
     */
    public String printException(DukeException e) {
        return printText(e.getMessage());
    }

    /**
     * Prints the provided String along with a top and bottom line.
     * @param s The String to be printed.
     */
    private String printText(String s) {
        return "\t____________________________________________________________"
                + s
                + "\t____________________________________________________________\n";
    }
}
