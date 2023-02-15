package duke;

import java.util.Scanner;



/**
 * Stores the scanner and handles what information to display to the user.
 */
public class Ui {

    private final Scanner sc;

    /**
     * Constructor for starting the Ui and launching Stage for JavaFX
     */
    public Ui() {
        greet();
        sc = new Scanner(System.in);
    }

    public String greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return logo + "Hello! I'm Duke\nWhat can I do for you?";
    }

    public String exit() {
        System.exit(0);
        return "\tBye. Hope to see you again soon!";
    }

    public String showError(DukeException err) {
        return "\t" + err.getMessage();
    }
}
