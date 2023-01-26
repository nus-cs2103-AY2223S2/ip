package core.exceptions;

/**
 * The exception that is thrown when an error occurs while writing to a file.
 */
public class WriteException extends Exception {
    /**
     * Creates a new write exception with the given message.
     *
     * @param message the message of the exception.
     */
    public WriteException(String message) {
        super(message);
    }
}
