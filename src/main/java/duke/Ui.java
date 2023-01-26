package duke;

/**
 * Ui class is used for all user interface interactions with
 * the user.
 *
 * @author      Tseng Chen-Yu
 * @version     %I%, %G%
 * @since       1.0
 */
public class Ui {
    /**
     * Constructor for Ui class, displaying greeting UI when user first starts the program.
     */
    public Ui() {
        final String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greeting = "______________________________________\n"
                + "Hey there buddy! I'm Duke. Your Personal Task Assistant!\n"
                + "What can I do for you today?\n"
                + "______________________________________\n";
        System.out.print(greeting);
    }

    /**
     * Convenient function for displaying arbitrary text to the user.
     *
     * @param textToDisplay Description for displaying to the user.
     */
    public void showResult(String textToDisplay) {
        System.out.print(textToDisplay);
    }
}
