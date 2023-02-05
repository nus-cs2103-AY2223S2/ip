package duke.exception;

/**
 * A DukeException that deals with invalid tasks loaded
 * from the saved file.
 */
public class InvalidTaskException extends DukeException {
    /**
     * Constructor for InvalidTaskException.
     */
    public InvalidTaskException() {
        super("This task is invalid!");
    }
}
