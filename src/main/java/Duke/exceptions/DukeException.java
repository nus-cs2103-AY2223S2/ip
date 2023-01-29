package Duke.exceptions;

/**
 * A wrapper around the Exception class for the Duke Chat bot.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}