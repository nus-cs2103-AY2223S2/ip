package duke.duke;

import java.util.Scanner;

/**
 * Deals with the reading user inputs and responding to their inputs.
 */
public class Ui {
    /**
     * Returns user inputs
     */
    public String readInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
