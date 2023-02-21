package cluck.ui;

import java.util.Scanner;

import cluck.messages.Messages;


/**
 * Cluck.Ui handles user commands and outputs messages from Cluck.Duke
 */
public class Ui {
    private final Scanner userInput = new Scanner(System.in);

    /**
     * Instantiates a new Ui.
     */
    public Ui() {}

    /**
     * Greets user.
     */
    public void greetUser() {
        System.out.println(Messages.MESSAGE_WELCOME);
    }

    /**
     * Bids user farewell.
     */
    public void farewellUser() {
        System.out.println(Messages.MESSAGE_GOODBYE);
    }

    /**
     * Reads a single line of user input.
     *
     * @return the string
     */
    public String readInput() {
        return userInput.nextLine();
    }

    /**
     * Print response after executing command.
     *
     * @param response the response
     */
    public void printResponse(String response) {
        System.out.println(response);
    }

}
