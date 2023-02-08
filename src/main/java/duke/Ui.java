package duke;

import java.awt.*;
import java.util.Scanner;

public class Ui {
    private Scanner scan;
    private final String DIVIDER = "____________________________________________________________";
    private final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Constructor for Ui class.
     */
    public Ui() {
        scan = new Scanner(System.in);
    }

    /**
     * Reads and returns user input.
     * @return User input from <code>System.in</code>.
     */
    public String readCommand() {
        return scan.nextLine();
    }

    /**
     * Prints welcome message for user.
     */
    public void showWelcome() {
        String welcomeMsg = LOGO + "\nHello! I'm Duke\nWhat can I do for you?";
        dukeSpeak(welcomeMsg);
    }

    /**
     * Prints error message for errors while loading tasks.
     */
    public void showLoadingError() {
        dukeSpeak("Unable to load tasks.");
    }

    /**
     * Formats and prints the message to user.
     *
     * @param message The message to be printed to the user.
     */
    public void dukeSpeak(String message) {
        String printStr = DIVIDER + "\n" + message + "\n" + DIVIDER;
        System.out.println(printStr);
    }

    /**
     * Clean-up operations for Ui class.
     */
    public void close() {
        scan.close();
    }
}
