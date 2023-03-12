package exceptions;

/**
 * SundayException is a custom exception class that extends the Exception class.
 * This exception will be thrown when a specific event related to Sunday occurs.
 *
 * @author Tan Yan-Hao Joshua
 */
public class SundayException extends Exception {

    /**
     * Constructor for SundayException that takes a string message as input.
     * This message will be passed to the parent class (Exception) via the super() call.
     *
     * @param message The message to be associated with the exception.
     */
    public SundayException(String message) {
        super(message);
    }
}
