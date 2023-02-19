package duke.exceptions;

/**
 * Exception used when user does not specify keywords where needed
 */
public class MissingKeywordException extends DukeException {
    /**
     * Constructor for a MissingKeywordException.
     */
    public MissingKeywordException() {
        super("Need a keyword to search for da yo~");
    }
}
