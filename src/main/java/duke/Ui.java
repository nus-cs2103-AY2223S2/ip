package duke;
import duke.dukeexceptions.DukeExceptions;

/**
 * Ui deals with interactions with the user.
 */
public class Ui {

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        String introduction = "  ________________________________\n"
                + "  Hello! I'm Duke\n"
                + "  What can I do for you?\n"
                + "  ________________________________\n";
        System.out.println(introduction);
    }

    /**
     * Prints the exit message.
     */
    public void outro() {
        String bye = "  Bye! have a great day\n";
        System.out.println(bye);
    }

    /**
     * Prints formatted exception.
     *
     * @param exception the exception to be printed
     */
    public void showError(DukeExceptions exception) {
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
    public void displayMessage(String message) {
        System.out.print(message);
    }
}
