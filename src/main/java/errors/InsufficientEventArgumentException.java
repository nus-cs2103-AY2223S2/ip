package errors;

/**
 * Exception class for indicating when a user provides insufficient details to a event command.
 * @author Nicholas Lee
 */

public class InsufficientEventArgumentException extends DukeRuntimeException{
    public InsufficientEventArgumentException(String message) {
        super(message);
    }
}
