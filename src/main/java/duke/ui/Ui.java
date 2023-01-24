package duke.ui;

/**
 * UI that deals with interactions.
 */
public class Ui {

    /**
     * Prints a line separator.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Duke");
        showLine();
    }

    /**
     * Prints the goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you soon!");
    }
}
