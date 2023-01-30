package core.exceptions;

/**
 * The exception that is thrown when an error occurs while disposing of a
 * resource.
 */
public class DisposableException extends Exception {
    /**
     * Creates a new disposable exception with the given message.
     *
     * @param message the message of the exception.
     */
    public DisposableException(String message) {
        super(message);
    }
}
