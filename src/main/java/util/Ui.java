package util;

import java.util.Scanner;

/**
 * Class to handle the User Interface of the Duke program.
 * @author Merrick
 */
public class Ui {
    private static final String divider = "    ____________________________________________________________";
    private final Scanner command;

    /**
     * Constructor of Ui class.
     */
    public Ui() {
        greetUser();
        this.command = new Scanner(System.in);
    }

    /**
     * Outputs greeting message to the User when Duke is booted up.
     */
    public void greetUser() {
        System.out.println(divider);
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println(divider);
    }

    /**
     * Reads in the input from the user.
     * @return String input from user.
     */
    public String nextInput() {
        return this.command.nextLine();
    }

    /**
     * Closes the Scanner object and displays end message to the User.
     */
    public void closeCommand() {
        System.out.println("    Bye. Hope to see you again soon!");
        this.command.close();
    }

    /**
     * Displays the error message from being unable to retrieve
     * task history data.
     */
    public void showLoadingError() {
        System.out.println("History unable to be retrieved!");
    }
}
