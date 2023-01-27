/**
 * File name: Ui.java
 * @author: Jerome Neo
 * Description: Ui class deals with interaction with the user.
 */
public class Ui {
    private final String LINE = "____________________________________________________________";
    private final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Constructor for Ui.
     */
    public Ui () {
    }

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
        System.out.println(LOGO);
        line();
        System.out.println("Hello from\n" + LOGO);
        line();
        System.out.println("Hello! I'm Duke");
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
