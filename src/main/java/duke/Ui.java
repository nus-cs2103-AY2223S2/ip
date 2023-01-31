package duke;

import java.util.Scanner;

/**
 * Represents an ui object for interaction with user.
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);

    /**
     * Greets the user.
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");
    }

    /**
     * Says goodbye to user.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Returns the next input line.
     * @return input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

}
