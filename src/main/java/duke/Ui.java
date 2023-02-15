package duke;

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
     * Prints entered text in a certain format.
     * @param input Text to be formatted.
     */
    public void printResponse(String input) {
        String horizLine = "_____________________________";
        System.out.printf("%s\n%s\n%s\n%n", horizLine, input, horizLine);
    }

    /**
     * Greets the user after the chatbot is started.
     */
    public String welcomeMessage() {
//        printResponse("Hello! I'm Interrobang\nWhat can I do for you today?");
        return "Hello! I'm Interrobang\nWhat can I do for you today?";
    }

    /**
     * Reads in a user entered line.
     * @return A string containing the full line entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows the user an error that occurred as a result of their commands.
     * @param e The exception that occurred.
     */
    public void showError(DukeException e) {
        printResponse(e.getMessage());
    }

    /**
     * Says bye to the user after the exit command is called.
     */
    public String sayBye() {

        return "Bye! Hope to see you again soon!";
    }
}
