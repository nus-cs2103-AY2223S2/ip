package duke.ui;

/**
 * Handles displaying of information to the user.
 */
public class Ui {
    /**
     * Prints a specified message.
     *
     * @param message The message to be printed.
     */
    public void print(String message) {
        System.out.println("    ____________________________________________________________");
        System.out.printf("     %s\n", message.replace("\n", "\n     "));
        System.out.print("    ____________________________________________________________\n\n");
    }
}
