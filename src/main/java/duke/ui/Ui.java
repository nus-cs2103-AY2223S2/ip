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
     * Prints out the given string to screen.
     */
    public void printMsg(String errMessage) {
        System.out.println(errMessage + "\n");
    }
}
