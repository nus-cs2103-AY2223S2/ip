package seedu.duke;

import java.util.Scanner;

/**
 * Deals with user interactions.
 */
public class Ui {

    public Ui() {};

    Scanner sc = new Scanner(System.in);

    /**
     * Gets the next instruction line from user
     *
     * @return user command
     */
    public String getNextCommand() {
        return sc.nextLine();
    }
}
