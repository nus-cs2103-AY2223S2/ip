package duke.ui;

/**
 *      File name: duke.ui.Ui.java
 *      @author: Jerome Neo
 *      Description: duke.ui.Ui class deals with interaction with the user.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Prints a line separator.
     */
    public void line() {
        System.out.println(LINE);
    }

    /**
     * Prints a greeting to the user.
     */
    public void greeting() {
        line();
        System.out.println("Hello from\n" + LOGO);
        line();
        System.out.println("Hello! I'm Jamie");
        System.out.println("What can I do for you?");
        line();
    }

    /**
     * Prints an exit message to the user.
     */
    public void farewell() {
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }

    public void announceFindResult() {
        System.out.println("Here's what we found. ");
    }

    /**
     * Prints to notify the user that an unexpected error has occured.
     */
    public void somethingWentWrong() {
        System.out.println("Invalid response. Please try: todo, deadline or event. :)");
    }

}
