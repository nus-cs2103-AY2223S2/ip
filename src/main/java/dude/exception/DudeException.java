package dude.exception;

/**
 * Custom exception for Dude Program.
 */
public class DudeException extends Exception {
    /**
     * Initializes DudeException.
     *
     * @param message Message of error.
     */
    public DudeException(String message) {
        super(message);
    }
}
