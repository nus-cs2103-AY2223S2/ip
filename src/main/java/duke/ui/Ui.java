package duke.ui;

import java.util.Scanner;

/**
 * Represents the test-based user interface of the chatbot.
 */
public class Ui {

    private static final String LINE = "------------------------------------------------------------";

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Greets the user upon starting the session with the chatbot.
     */
    public void greet() {
        System.out.println("\nHello from\n" + Ui.LOGO);

        System.out.println("How can I help you?\n");
        System.out.println(Ui.LINE + "\n");

    }

    /**
     * Prompts the user for and obtains a text input.
     * Sets up the interface for the chatbot's response and returns the user input.
     *
     * @param commandScanner The Scanner object used to obtain user's text input.
     * @return User's text input
     */
    public String getUserCommand(Scanner commandScanner) {

        System.out.print("You:\n");
        String command = commandScanner.nextLine();

        System.out.println("\nDuke:");

        return command;

    }

    /**
     * Sets up the interface to obtain the user's next input.
     */
    public void endCommand() {
        System.out.println("\n" + Ui.LINE + "\n");
    }


}
