package duke;

import java.util.Scanner;

/**
 * Interface class that handles input accepting and output display
 */
public class UserInterface {

    public static final String EXIT_MESSAGE = "Farewell. Always at your service.";
    public static final String GREET_MESSAGE = "Hello, I'm Ekud! What can I do for you?";
    public static final String INDENT = "    ";
    private static final String LOGO =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String FORMATTER_LEFT_WRAP = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    private static final String FORMATTER_RIGHT_WRAP = "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";

    private Scanner scanner;

    /**
     * Constructor for User Interface class to initialize Scanner
     */
    UserInterface() {
        scanner = new Scanner(System.in);
    }

    public String getInput() {
        return scanner.nextLine();
    }

    public void displayResult(String result) {
        printFormattedMessage(result);
    }

    public void showExceptionMessage(Exception exception) {
        printFormattedMessage(exception.getMessage());
    }

    public void showStartMessage() {
        System.out.println(LOGO);
        printFormattedMessage(GREET_MESSAGE);
    }
    public void showExitMessage() {
        printFormattedMessage(EXIT_MESSAGE);
    }

    private void printFormattedMessage(String message) {
        System.out.println(FORMATTER_LEFT_WRAP + message + FORMATTER_RIGHT_WRAP);
    }
}
