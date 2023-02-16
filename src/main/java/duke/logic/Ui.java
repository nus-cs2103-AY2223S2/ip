package duke.logic;

import duke.exceptions.DukeException;

import java.util.Scanner;

/**
 * Represents the user interface that the user interacts with.
 * @author lukkesreysandeur
 */
public class Ui {
    private Scanner scanner;

    /**
     * Initialises the Ui object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Greets the user after the chatbot is started.
     */
    public String welcomeMessage() {
        return "Hello! I'm Interrobang\nWhat can I do for you today?" +
                "\n\nEnter 'help' to view all commands!";
    }

    /**
     * Shows the user an error that occurred as a result of their commands.
     *
     * @param e The exception that occurred.
     * @return The error message.
     */
    public String showError(DukeException e) {
        return e.getMessage();
    }

    /**
     * Says bye to the user after the exit command is called.
     */
    public String sayBye() {
        return "Bye! Hope to see you again soon!";
    }
}
