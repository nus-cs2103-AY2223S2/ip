package duke.duke;

import java.util.Scanner;

/**
 * Deals with the reading user inputs and responding to their inputs.
 */
public class Ui {

    public void start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Greetings");
    }
    /**
     * Returns user inputs
     */
    public String readInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
