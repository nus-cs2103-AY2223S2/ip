package wessy.exceptions.integer;

import wessy.exceptions.WessyException;

/**
 * NotAnIntegerException is an exception that should be thrown when the user
 * wants to mark, unmark or delete a task, and he inputs some strings that could
 * not be parsed into an integer.
 */
public class NotAnIntegerException extends WessyException {
    /**
     * Constructs an instance of NotAnIntegerException.
     */
    public NotAnIntegerException() {
        super("What you just input is not an integer. Please input an integer.");
    }
}
