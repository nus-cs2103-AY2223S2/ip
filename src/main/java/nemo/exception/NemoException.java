package nemo.exception;

/**
 * Exception class for all Nemo-related exceptions.
 *
 * @author Lian Kok Hai
 */
public class NemoException extends Exception {
    /**
     * Constructs new NemoException with given error message.
     *
     * @param message Error message.
     */
    public NemoException(String message) {
        super(message);
    }
}
