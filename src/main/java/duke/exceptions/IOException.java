package duke.exceptions;

/**
 * Represents an IO exception.
 * 
 * @author Samarth Verma
 */
public class IOException extends Exception {
    /**
     * Creates an IO exception.
     * 
     * @param message The error message.
     */
    public IOException(String message) {
        super(message);
    }

    /**
     * Creates an IO exception.
     * 
     * @param message The error message.
     * @param cause   The cause of the exception.
     */
    public IOException(String message, Throwable cause) {
        super(message, cause);
    }
}
