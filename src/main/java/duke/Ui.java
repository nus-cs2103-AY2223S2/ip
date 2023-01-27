package duke;

import java.util.Scanner;

/**
 * A class to handle all User Interactions: getting command input and printing output.
 */
public class Ui {
    /** Scanner to get user input */
    private Scanner scanner;

    /**
     * Constructor for Ui class.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Welcomes the user by printing a message and the Logo
     */
    public void open() {
        System.out.println("Welcome to\n" + Values.LOGO);
        System.out.println("Enter a command to start.\n");
    }

    /**
     * Wishes the user goodbye and closes the Scanner.
     */
    public void close() {
        pixlPrint("Goodbye! See you again :)");
        System.out.println(Values.HLINE);
        scanner.close();
    }

    /**
     * Prints some text as PixlBot.
     * @param text Text to print.
     */
    public void pixlPrint(String text) {
        System.out.println(Values.COLOR_PURPLE + "PixlBot: " + Values.COLOR_RESET + text);
        System.out.println(Values.HLINE);
    }

    /**
     * Prints some text as PixlBot, in the given color.
     * @param text Text to print.
     * @param color ANSI color code for text.
     */
    public void pixlPrint(String text, String color) {
        System.out.println(Values.COLOR_PURPLE + "PixlBot: " + color + text + Values.COLOR_RESET);
        System.out.println(Values.HLINE);
    }

    /**
     * Special print method to display the message from an exception.
     * @param e Exception to display.
     */
    public void pixlPrintException(Exception e) {
        pixlPrint("Uh oh! " + e.getMessage(), Values.COLOR_RED);
    }

    /**
     * Gets a new command from standard input.
     * @return The command received, as is.
     */
    public String getNewCommand() {
        System.out.print("You: ");
        return scanner.nextLine();
    }
}
