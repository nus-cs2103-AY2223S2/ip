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
    public String greetUser() {
        System.out.println(divider);
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println(divider);
        return divider + "    Hello! I'm Duke\n" + "    What can I do for you?\n" + divider;
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
    public String closeCommand() {
        this.command.close();
        return "    Bye. Hope to see you again soon!";
    }

    /**
     * Outputs required message for the User.
     * @return String output of text.
     */
    public String userOutput(String input) {
        System.out.println(input);
        return input;
    }

    /**
     * Displays the error message from being unable to retrieve
     * task history data.
     */
    public String showLoadingError() {
        return "History unable to be retrieved!";
    }
}
