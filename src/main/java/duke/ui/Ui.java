package duke.ui;

import java.util.Scanner;

/**
 * Deals with interactions with the user
 */
public class Ui {
    /**
     * Returns the string inputted by the user.
     *
     * @return String inputted by the user.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints a line.
     */
    public void showLine() {
        System.out.println("_______");
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        System.out.println("Hello, I am Duke. \nWhat can I do for you?");
    }

    /**
     * Prints farewell message when user exits.
     */
    public void farewellMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

}

