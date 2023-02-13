package henz.henzexception;

/**
 * CommandException class extends DukeException class.
 */
public class CommandException extends HenzException {

    /**
     * Constructor.
     * @param message the error message
     */
    public CommandException(String message) {
        super(message);
    }
}
