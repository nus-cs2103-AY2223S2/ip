package errors;

/**
 * Exception class for indicating when a user provides an invalid bot command.
 * @author Nicholas Lee
 */
public class DukeInvalidCommandException extends DukeRuntimeException{
    public DukeInvalidCommandException(String message) {
        super(message);
    }
}
