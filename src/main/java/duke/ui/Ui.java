package duke.ui;

import java.util.Scanner;

/**
 * Represents the user interface that the user interacts with, displaying messages and reading in inputs.
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Prints out a welcome message for the user.
     */
    public static String showWelcomeMessage() {
        return "Hello from Duke managed by Wesley Teo!\nWhat can I do for you?\n";
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showLine() {
        System.out.println("---------------------------------");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}
