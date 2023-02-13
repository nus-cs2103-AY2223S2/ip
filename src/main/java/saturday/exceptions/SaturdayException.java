package saturday.exceptions;
/**
 * The SaturdayException class is a custom exception class that extends the IllegalArgumentException class.
 * It is thrown when an illegal argument is passed to a method or constructor in the saturday package.
 *
 * @author Titus Lowe
 * @version 0.1
 */
public class SaturdayException extends IllegalArgumentException {

    /**
     * Constructs a new SaturdayException with the specified message.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
     */
    public SaturdayException(String message) {
        super(message);
    }
}
