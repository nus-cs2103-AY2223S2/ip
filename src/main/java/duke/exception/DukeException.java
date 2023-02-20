package duke.exception;

/**
 * An exception thrown when an error to be printed to output occurs during the app's execution.
 */
public class DukeException extends Exception {
    /**
     * Creates a DukeException object.
     *
     * @param message The error message to be printed to output.
     */
    public DukeException(String message) {
        super(String.format("Do you take me for a FOOL?!\n%s", message));
    }
}
