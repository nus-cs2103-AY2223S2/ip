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
    public String greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo + "What can I do for you?\n";
    }

    /**
     * Says goodbye to user.
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns the next input line.
     * @return input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

}
