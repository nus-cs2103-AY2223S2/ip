package duke;

import java.util.Scanner;

/**
 * Ui deals with interactions with the user (e.g. displays pretty-formatted messages).
 */
public class Ui {
    private Scanner userInput;

    /**
     * Constructor for Ui to instantiate scanner for user text input.
     */
    public Ui() {
        this.userInput = new Scanner(System.in);
    }

    /**
     * Displays a greeting message to the user at the start of the chat session.
     */
    public void showWelcome() {
        String greeting = "Yo i'm SmartDuke.\n"
                + "     how can i help you?";
        System.out.println(greeting);
    }

    /**
     * Displays a success message when a successful task is completed by Duke.
     * @param succMsg The success message.
     */
    public void showSuccess(String succMsg) {
        System.out.println(succMsg);
    }

    /**
     * Displays an error message when there is a failure encountered by Duke.
     * @param errMsg The error message.
     */
    public void showError(String errMsg) {
        System.out.println(errMsg);
    }

    /**
     * Displays a line to differentiate between two adjacent chat replies.
     */
    public void showLine() {
        System.out.println("--------------------------");
    }

    /**
     * Reads the next line of input by the user.
     * @return The input string by the user.
     */
    public String readCommand() {
        String userCommand = this.userInput.nextLine();
        return userCommand;
    }

    /**
     * Closes the scanner object so that no more input is accepted. This is called
     * at the end of the chat session.
     */
    public void close() {
        this.userInput.close();
    }
}
