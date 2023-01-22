package dukeexception;

/**
 * Exception for handling invalid date formats.
 */
public class InvalidDateException extends DukeException {
    /**
     * Constructor for InvalidDateException.
     */
    public InvalidDateException() {
        super("The date wrong format. FAILURE!", null);
    }
}
