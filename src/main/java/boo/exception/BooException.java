package boo.exception;

/**
 * Represents an exception related to the Boo chatbot.
 */
public class BooException extends Exception {
    /**
     * Constructs an exception message indicating the reason for exception.
     *
     * @param exceptionMessage Message to be displayed when the exception occurs.
     */
    public BooException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
