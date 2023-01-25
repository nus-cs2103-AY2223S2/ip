package duke.ui;

import java.util.Scanner;
/**
 * Handles interaction with user.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints out the welcome message onto the screen.
     */
    public void printWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hi! I am Duke. How may I help you today?\n");
    }

    /**
     * Prints out goodbye message onto screen.
     */
    public void printBye() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    /**
     * Reads input from the user.
     *
     * @return Returns the input string read.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Prints out the given string to screen.
     */
    public void print(String errMessage) {
        System.out.println(errMessage + "\n");
    }
}
