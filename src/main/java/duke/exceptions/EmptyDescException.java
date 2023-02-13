package duke.exceptions;

/**
 * Exception class for generic Tasks having no descriptor from user.
 */
public class EmptyDescException extends Exception {
    public EmptyDescException(String message) {
        super(message);
    }

    public EmptyDescException(Throwable throwable) {
        super(throwable);
    }

    public EmptyDescException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
