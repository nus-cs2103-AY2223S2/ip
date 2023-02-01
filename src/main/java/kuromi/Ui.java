package kuromi;

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
    public String showError(String msg) {
        return (msg);
    }

    /**
     * Show the lines for separation.
     */
    public String showLine() {
        return (this.lines);
    }

    /**
     * Show new line.
     */
    public String showEnter() {
        return "\n";
    }

    /**
     * Show error if cannot load the file that is in the hard disk.
     */
    public String showLoadingError() {
        return ("Cannot load file. :(\n");
    }

    /**
     * Show welcome message.
     */
    public String showWelcome() {
        return ("Hello! I'm Kuromi\nWhat can I do for you?\n");
    }

    /**
     * Show a message to the user.
     * @param msg Message to be shown.
     */
    public String show(String msg) {
        return (msg);
    }
}
