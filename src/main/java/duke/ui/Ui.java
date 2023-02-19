package duke.ui;
import duke.dukeexceptions.DukeExceptions;

/**
 * Ui deals with interactions with the user.
 */
public class Ui {

    /**
     * Prints the welcome message.
     */
    public static String showWelcome() {
        String introduction = "  ________________________________\n"
                + "  Yo Great to see you!\n"
                + "  What can I do for you?\n"
                + "  ________________________________\n";
        System.out.println(introduction);

        return "  Yo Great to see you!";
    }

    /**
     * Prints the exit message.
     */
    public static void outro() {
        String bye = "  Bye! have a great day\n";
        System.out.println(bye);
    }

    /**
     * Prints formatted exception.
     *
     * @param exception the exception to be printed
     */
    public static void showError(DukeExceptions exception) {
        String reply = exception.toString();
        System.out.println(reply);
    }


    public void showLine() {
        System.out.println("  ________________________________");
    }

    /**
     * Prints formatted messages.
     *
     * @param message the string to be printed
     */
    public static void displayMessage(String message) {
        System.out.print(message);
    }
}
