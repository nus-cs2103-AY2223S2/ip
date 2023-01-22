package dukeexception.commandexception;

import dukeexception.DukeException;

/**
 * Exception for handling an unknown command.
 */
public class UnknownCommandException extends DukeException {
    /**
     * Constructor for UnknownCommandException.
     */
    public UnknownCommandException() {
        super("HUH? What you say?", null);
    }
}
