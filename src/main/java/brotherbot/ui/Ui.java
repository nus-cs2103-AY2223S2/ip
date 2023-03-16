package brotherbot.ui;

import brotherbot.exceptions.BroException;

import java.util.Scanner;

public class Ui {
    private Scanner inputScanner;
    private String currInput;

    /**
     * Constructor for Ui object.
     */
    public Ui() {
        this.inputScanner = new Scanner(System.in);

    }

    /**
     * Reads the next input from inputScanner.
     *
     * @return String representation of next input.
     */
    public String readCommand() {
        this.currInput = inputScanner.nextLine();
        return currInput;
    }

    /**
     * Displays output string to user.
     *
     * @param output Output to be displayed to user.
     */
    public void toUser(String output) {
        System.out.println(output);
    }

    /**
     * Displays welcome message to user.
     */
    public void showWelcome() {
        System.out.println("Hello Brother\nWelcome to Brother Bot\nWhats up what can I do for you mi amigo");
    }

    /**
     * Displays BroException error message to user.
     *
     * @param x BroException to be conveyed to user.
     */
    public void showError(BroException x) {
        System.out.println(x.getMessage());
    }

    /**
     * Displays file loading error message to user.
     */
    public void showLoadingError() {
        System.out.println("An error occurred while creating the new file: data.txt");
    }
}
