package errors;

/**
 * Exception class for indicating when a user provides insufficient details to a deadline command.
 * @author Nicholas Lee
 */

public class InsufficientDeadlineArgumentException extends DukeRuntimeException{
    public InsufficientDeadlineArgumentException(String message) {
        super(message);
    }
}
