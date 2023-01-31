package cbot.io;

/**
 * Signals that the user input was improper.
 */
public class PoorInputException extends Exception {

    /**
     * Constructs a PoorInputException with the specified detail message.
     *
     * @param message The detail message.
     */
    public PoorInputException(String message) {
        super(message);
    }
}
