package kal;

import kal.commands.Command;
import kal.kalexception.KalException;

/**
 * This class handles user interactions/messages.
 */
public class Ui {
    private static final String WELCOME = "Hello! I am Kal. I'm your best calendar pal. "
            + "Let's start doing some work!";
    private static final String GOODBYE = "Auf Wiedersehen!";

    /**
     * Retrieves the welcome message.
     *
     * @return a string representing the welcome message.
     */
    public static String getWelcome() {
        return Ui.WELCOME;
    }

    /**
     * Retrieves the farewell message.
     *
     * @return a string representing the farewell message.
     */
    public static String getGoodbye() {
        return Ui.GOODBYE;
    }

    /**
     * Prints a welcome message.
     */
    public void printWelcome() {
        System.out.println(Ui.WELCOME);
    }

    /**
     * Prints a farewell message.
     */
    public void printGoodbye() {
        System.out.println(Ui.GOODBYE);
    }

    /**
     * Prints a descriptive message of a command.
     *
     * @param command The command to be described.
     */
    public void printCommandMessage(Command command) {
        System.out.println(command);
    }

    /**
     * Generates a descriptive message of a command.
     *
     * @param command The command to be described.
     */
    public String getCommandMessage(Command command) {
        return command.getResponseOutput();
    }

    /**
     * Prints a descriptive message of a KalException.
     *
     * @param e The KalException to be described.
     */
    public void printExceptionMessage(KalException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Generates a descriptive message of a KalException.
     *
     * @param e The KalException to be described.
     */
    public String getExceptionMessage(KalException e) {
        return e.getMessage();
    }
}
