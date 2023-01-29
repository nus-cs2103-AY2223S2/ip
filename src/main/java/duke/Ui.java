package duke;

import java.util.Scanner;

/**
 * Deals with interactions with the user (e.g. reading inputs, showing outputs).
 */
public class Ui {
    private String lines = "____________________________________________________________";

    /**
     * Read user's inputs.
     * @return User input as a string.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Show errors occurred to the user.
     * @param msg Error message to be shown.
     */
    public void showError(String msg) {
        System.out.println(msg);
    }

    /**
     * Show the lines for separation.
     */
    public void showLine() {
        System.out.println(this.lines);
    }

    /**
     * Show new line.
     */
    public void showEnter() {
        System.out.println();
    }

    /**
     * Show error if cannot load the file that is in the hard disk.
     */
    public void showLoadingError() {
        System.out.println("Cannot load file. :(\n");
    }

    /**
     * Show welcome message.
     */
    public void showWelcome() {
        this.showLine();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        this.showLine();
        this.showEnter();
    }

    /**
     * Show a message to the user.
     * @param msg Message to be shown.
     */
    public void show(String msg) {
        System.out.println(msg);
    }
}
