package duke;

import java.util.Scanner;

/**
 * Encapsulates the interaction between user and program.
 */
public class Ui {
    /**
     * Displays a welcome message to the user.
     *
     * @return msg the welcome message
     */
    public String showWelcome() {
        String msg = "Hello! I'm Chiwa, your personal chatbot (◔◡◔✿) \n";
        msg += "What can I do for you today? \n";
        return msg;
    }

    /**
     * Reads the command entered by the user.
     *
     * @return the input to be parsed
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public String showError(Exception e) {
        return e.getMessage();
    }
}
