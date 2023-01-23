package exceptions;

/**
 * Exception for Duke-specific operations.
 */
public class DukeException extends RuntimeException {
    public DukeException(String message, Throwable error) {
        super(message, error);
    }
}
