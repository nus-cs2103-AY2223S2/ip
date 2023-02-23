package duke;

import java.util.Scanner;

/**
 * Ui class to handle user interaction for Duke.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructor for Ui instance of Duke.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Returns user input as a String.
     *
     * @return String input by the user.
     */
    public String requestUserInput() {
        return scanner.nextLine();
    }

    /**
     * Prints a loading error message to user.
     */
    public static void showLoadingErrorMessage() {
        System.out.println("Couldn't load the file for some reason");
    }

    /**
     * Prints a message to welcome a new user, if no save file is found.
     */
    public static void showNewUserMessage() {
        System.out.println("Oh boy a new user! What's up?");
    }

    /**
     * Prints a prompt for the user to input command.
     */
    public static void showWelcomePrompt() {
        System.out.println("How might I help you today?");
    }

    /**
     * Prints the Duke logo and introduces Duke.
     */
    public String showWelcomeMessage() {
        String projName = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Yo! The name is\n" + projName;
    }
}
