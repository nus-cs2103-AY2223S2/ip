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
     * Prints greeting for user.
     */
    public void greet() {
        printText("\t Hello! I'm Duke\n\t What can I do for you?");
    }

    /**
     * Reads 1 line of user input through the command line and returns it.
     * @return The user input that is read as a String.
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Prints the message String that was provided.
     * @param s The message String.
     */
    public void printResponse(String s) {
        printText(s);
    }

    /**
     * Prints the message in the DukeException that was provided.
     * @param e The DukeException.
     */
    public void printException(DukeException e) {
        printText(e.getMessage());
    }

    /**
     * Prints the provided String along with a top and bottom line.
     * @param s The String to be printed.
     */
    private void printText(String s) {
        System.out.println("\t____________________________________________________________");
        System.out.println(s);
        System.out.println("\t____________________________________________________________\n");
    }
}
