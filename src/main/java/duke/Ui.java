package duke;

import java.util.Scanner;

/**
 * The class to interact with users.
 */
public class Ui {
    static final String LINE = "____________________________________________________________";

    /**
     * To welcome users.
     */
    public void hello() {
        System.out.println(LINE);
        System.out.println("Hello! I'm T-Rex. Roarrrrrrrrrrrrrr!");
        System.out.println("What do you need from me?");
        System.out.println(LINE);
        System.out.println();
    }

    /**
     * To read user input.
     *
     * @return the user's input
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * To print a long line to divide the interface.
     */
    public void printLine() {
        System.out.println(LINE);
    }
}
