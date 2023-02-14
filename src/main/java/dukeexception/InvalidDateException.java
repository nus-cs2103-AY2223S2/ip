package dukeexception;

/**
 * Exception for handling incorrect date formats.
 */
public class InvalidDateException extends DukeException {
    /**
     * Constructor for InvalidDateException.
     */
    public InvalidDateException() {
        super("Haiya this not date. FAILURE!", null);
    }
}
