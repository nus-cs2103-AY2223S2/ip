package utils;

import java.util.Scanner;

/**
 * Represents a class that handles all user interactions with TreeBot.
 */
public class Ui {
    private String welcomeString = "Hello, I'm a tree. How may I be of service?";
    private Scanner sc = new Scanner(System.in);

    /**
     * Prints the welcome line.
     */
    public void showWelcome() {
        System.out.println(welcomeString);
    }

    /**
     * Reads user commands from CLI.
     * @return user input in a string format.
     */
    public String readCommand() {
        return sc.nextLine();
    }


}

