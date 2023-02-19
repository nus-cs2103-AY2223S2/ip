package cluck.ui;

import java.util.Scanner;

import cluck.messages.Messages;



/**
 * Cluck.Ui handles user commands and outputs messages from Cluck.Duke
 **/
public class Ui {
    private final Scanner userInput = new Scanner(System.in);

    public Ui() {}

    public void greetUser() {
        System.out.println(Messages.MESSAGE_WELCOME);
    }

    public void farewellUser() {
        System.out.println(Messages.MESSAGE_GOODBYE);
    }

    public String readInput() {
        return userInput.nextLine();
    }

    public void printResponse(String response) {
        System.out.println(response);
    }

}
