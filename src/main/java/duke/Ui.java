package duke;

import java.util.Scanner;

/**
 * Represents the user interface that the user interacts with, displaying messages and reading in inputs.
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Prints out a welcome message for the user.
     */
    public void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";
        String line = "---------------------------------";
        System.out.println("Hello from\n" + logo + "  managed by Wesley Teo.\n\nWhat can I do for you?\n" + line);
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
