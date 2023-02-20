package duke.gui;

import duke.Values;

/**
 * A class to handle all User Interactions: getting command input and printing output.
 */
public class Ui {

    /**
     * Constructor for Ui class.
     */
    public Ui() {
    }

    /**
     * Welcomes the user by printing a message and the Logo
     */
    public String open() {
        return "Welcome to PixlBot\n"
            + "Enter a command to start.\n";
    }

    /**
     * Wishes the user goodbye and closes the Scanner.
     */
    public String close() {
        return pixlPrint("Goodbye! See you again :)");
    }

    /**
     * Prints some text as PixlBot.
     * @param text Text to print.
     */
    public String pixlPrint(String text) {
        return "PixlBot says:\n" + text;
    }

    /**
     * Prints some text as PixlBot, in the given color.
     * @param text Text to print.
     * @param color ANSI color code for text.
     */
    public String pixlPrint(String text, String color) {
        return "PixlBot says:\n" + text;
    }

    /**
     * Special print method to display the message from an exception.
     * @param e Exception to display.
     */
    public String pixlPrintException(Exception e) {
        return pixlPrint("Uh oh! " + e.getMessage());
    }
}
