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
     * Output greeting message to the User when Duke is booted up.
     * @return Greeting message to user.
     */
    public String greetUser() {
        System.out.println(divider);
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println(divider);
        return divider + "    Hello! I'm Duke\n" + "    What can I do for you?\n" + divider;
    }

    /**
     * Closes the Scanner object and displays end message to the User.
     * @return Goodbye message to User.
     */
    public String closeCommand() {
        this.command.close();
        return "    Bye. Hope to see you again soon!";
    }
}
