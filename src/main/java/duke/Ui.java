package duke;
import java.util.Scanner;

/**
 * Ui to communicate with the user.
 */
public class Ui {
    private Scanner sc;

    /**
     * Class constructor of Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints the greeting message.
     */
    public static String greeting() {
        return "Woof! What would you want to do today?\nPlease enter 'guide' for help!";
    }

    /**
     * Prints the content of the DukeException.
     * @param error the DukeException to be shown to the user
     */
    public String showError(DukeException error) {
        return error.toString();
    }

    /**
     * Prints the error that occurs during loading the data file.
     */
    public String showLoadingError() {
        return "The file does not exist.";
    }

    /**
     * Prints the user guide for different commands of Duke.
     */
    public String showGuide() {
        String guide = "Woof! You can try these commands \n\n";
        guide += "list\n";
        guide += "todo (content)\n";
        guide += "deadline (content) /by (dd/MM/yyyy HH:mm)\n";
        guide += "event (content) /from (dd/MM/yyyy HH:mm) /to (dd/MM/yyyy HH:mm)\n";
        guide += "mark (index)\n";
        guide += "unmark (index)\n";
        guide += "delete (index)\n";
        guide += "delete all\n";
        guide += "bye\n";
        return guide;
    }

    /**
     * Prints the farewell message.
     */
    public static String end() {
        return "Woof! Hope to see you again soon!";
    }
}
