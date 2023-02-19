package duke.exceptions;

/**
 * Exception thrown when supplied date is invalid.
 */
public class InvalidDateException extends DukeException {
    /**
     * Constructor for an InvalidDateException.
     */
    public InvalidDateException() {
        super("Not a valid date da yo~\n" +
                "I recognise YYYY-MM-DD [HH:mm] date formats~");
    }
}
