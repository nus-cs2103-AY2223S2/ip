package duke.ui;

import java.util.Scanner;

/**
 * Encapsulates the related fields and behavior of the Ui
 * that handles interaction with user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Instantiates Ui.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
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
     * @return The input string read.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Prints out the given string to screen.
     */
    public void printMsg(String errMessage) {
        System.out.println(errMessage + "\n");
    }
}
