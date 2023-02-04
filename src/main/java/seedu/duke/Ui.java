package seedu.duke;

import java.util.Scanner;

/**
 * Deals with user interactions.
 */
public class Ui {

    public Ui() {};

    /**
     * Gets the next instruction line from user.
     *
     * @return user command
     */
    public String getNextCommand() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        sc.close();
        return command;
    }
}
