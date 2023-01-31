package membot.utils;

/**
 * Represents an <code>Exception</code> when the command input is invalid.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String msg) {
        super(String.format("[Invalid command] %s", msg));
    }
}
