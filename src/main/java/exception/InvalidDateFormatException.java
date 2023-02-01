package exception;

/**
 * Represents the exception when a date does not follow the Duke format.
 */
public class InvalidDateFormatException extends DukeException {

    /**
     * Constructor for InvalidDateFormatException.
     * @param errorMessage Message for the error.
     */
    public InvalidDateFormatException(String errorMessage) {
        super(errorMessage);
    }
}