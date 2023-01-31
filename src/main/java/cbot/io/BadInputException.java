package cbot.io;

/**
 * Signals that the user input was improper, and likely erroneous.
 */
public class BadInputException extends PoorInputException {

    /**
     * Constructs a BadInputException with the specified detail message.
     *
     * @param message The detail message.
     */
    public BadInputException(String message) {
        super(message);
    }
}
