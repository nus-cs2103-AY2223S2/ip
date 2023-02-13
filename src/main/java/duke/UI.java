package duke;

import java.util.Scanner;

/**
 * User interface class that formats the UI in the terminal.
 */
public class UI {
    private Scanner sc;

    /**
     * Constructor to create a new UI object.
     */
    public UI() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Calls to the scanner to take in the next user input.
     * @return The user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the farewell message to the UI.
     */
    public String getFarewellMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints to the UI an error message, if duke.Duke is being run for the first time and the required data files to
     * read from do not exist.
     */
    public void showLoadingError() {
        System.out.println("Running duke.Duke for the first time - Creating required data files");
    }
}
