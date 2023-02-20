package duke.dukeExcpetion;

/**
 * {@code DukeException} class encapsulates exceptions that
 * are to be handled in {@code Duke} class
 */
public class DukeException extends Exception {
    /**
     * Message to be displayed from exception
     */
    protected String errorMessage;

    /**
     * Constructor method for DukeException class
     * @param message message to be displayed from exception
     */
    public DukeException(String message) {
        this.errorMessage = message;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
