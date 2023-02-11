package errors;

/**
 * Exception class for indicating when a user provides insufficient details to a bot command.
 * @author Nicholas Lee
 */
public class DukeInsufficientArgumentException extends DukeRuntimeException {
    public DukeInsufficientArgumentException(String message) {
        super(message);
    }
}
