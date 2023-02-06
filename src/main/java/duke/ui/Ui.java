package duke.ui;

import java.util.Scanner;

/**
 * Ui that deals with interaction with the user.
 */
public class Ui {
    protected Scanner sc;

    /**
     * Constructor for the Ui class.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Reads command that have been inputted by the User.
     * @return String of the user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays a line to divide the output by Duke.
     */
    public void showLine() {
        System.out.println("_______________________");
    }

    /**
     * Display error messages.
     * @param err the error message to display.
     */
    public void showError(String err) {
        System.out.println(err);
    }

    /**
     * Display messages.
     * @param msg the message to display.
     */
    public void show(String msg) {
        System.out.println(msg);
    }
}
