package errors;

/**
 * Exception class for indicating when a user provides insufficient details to a to-do command.
 * @author Nicholas Lee
 */

public class InsufficientToDoArgumentException extends DukeRuntimeException{
    public InsufficientToDoArgumentException(String message) {
        super(message);
    }
}
