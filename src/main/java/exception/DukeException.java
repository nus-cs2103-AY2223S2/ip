package exception;

/**
 * Represents an exception unique to the Duke application.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException.
     * @param errorMessage Message for the error.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}