package ui;

import java.util.Scanner;

/**
 * Class which handles the interaction between the Duke and the user.
 */
public class Ui {

    private static final String WELCOME_MSG = "Greetings! JEDI GRANDMASTER YODA here\n" + "For you, What can I do?";
    private static final String BYE_MSG = "Be Gone, You Must. May the Force be with You!";
    /**
     * Displays the Goodbye message.
     */
    public void displayByeMessage() {
        System.out.println(BYE_MSG);
    }

    /**
     * Listens to standard input for a command.
     * @param scanner Scanner object to read the input
     * @return String input
     */
    public String readCommand(Scanner scanner) {
        return scanner.nextLine();
    }
    public String getWelcomeMsg() {
        return WELCOME_MSG;
    }
    public String getByeMsg() {
        return BYE_MSG;
    }
    /**
     * Shows exception message thrown to the UI.
     * @param exception Exception for UI to display error message
     */
    public void showLoadingErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
