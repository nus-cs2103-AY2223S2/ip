package duke.ui;

/**
 * Handles displaying of messages to the user.
 */
public interface Ui {
    /**
     * Prints a specified message.
     *
     * @param message The message to be printed.
     */
    void print(String message);

    /**
     * Returns the input obtained from the user.
     *
     * @return The input obtained from the user.
     */
    String getInput();

    /**
     * Handles any clean up needed when closing the UI.
     */
    void close();
}
