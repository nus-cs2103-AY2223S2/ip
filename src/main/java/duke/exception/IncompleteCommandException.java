package duke.exception;

/**
 * A DukeException that deals with incomplete command entries.
 */
public class IncompleteCommandException extends DukeException {
    /**
     * Constructor for IncompleteCommandException.
     */
    public IncompleteCommandException() {
        super("The command entry is incomplete!");
    }
}
