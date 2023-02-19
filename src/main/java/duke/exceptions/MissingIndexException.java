package duke.exceptions;

/**
 * Exception used when user does not specify indices where needed
 */
public class MissingIndexException extends DukeException {
    /**
     * Constructor for a MissingIndexException.
     */
    public MissingIndexException() {
        super("Need an index da yo~");
    }
}
