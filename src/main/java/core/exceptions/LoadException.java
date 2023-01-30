package core.exceptions;

/**
 * The exception that is thrown when an error occurs while loading from a file.
 */
public class LoadException extends Exception {
    /**
     * Creates a new load exception with the given message.
     *
     * @param message the message of the exception.
     */
    public LoadException(String message) {
        super(message);
    }
}
