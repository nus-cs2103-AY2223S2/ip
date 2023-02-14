package dukeexception.commandexception;

import dukeexception.DukeException;

/**
 * Exception for handling unknown commands written by the user.
 */
public class UnknownCommandException extends DukeException {
    /**
     * Constructor for UnknownCommandException.
     */
    public UnknownCommandException() {
        super("HUH? What you say?", null);
    }
}
