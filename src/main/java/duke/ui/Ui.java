package duke.ui;

import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);
    private static String formatMessage(String message) {
        String FORMAT_LINE = "___________________________";
        return FORMAT_LINE + "\n"
                + message + "\n"
                + FORMAT_LINE;
    }

    /**
     * Displays a formatted message.
     * @param message The message to display.
     */
    public void printMessage(String message) {
        System.out.println(formatMessage(message));
    }

    /**
     * Gets a line of user input.
     * @return The user input.
     */
    public String getInputFromUser() {
        return scanner.nextLine();
    }

    /**
     * Prints the prompt for user input.
     */
    public void printPromptForInput() {
        System.out.print(">");
    }

    /**
     * Prints a greeting.
     */
    public void greet() {
        printMessage("Hello, I am Duke.\n"
                + "What can I do for you?");
    }

    /**
     * Prints a goodbye.
     */
    public void sayGoodbye() {
        printMessage("Goodbye. I hope to see you again.");
    }
}
