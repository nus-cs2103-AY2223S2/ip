package exception;

/**
 * Represents the exception when a date does not follow the Duke format.
 */
public class InvalidDateFormatException extends DukeException{

    public InvalidDateFormatException(String errorMessage) {
        super(errorMessage);
    }
}
