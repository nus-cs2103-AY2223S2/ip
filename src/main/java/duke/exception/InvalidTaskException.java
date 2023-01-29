package duke.exception;

public class InvalidTaskException extends DukeException {
    /**
     * Constructor for InvalidTaskException.
     */
    public InvalidTaskException() {
        super("This task is invalid!");
    }
}
