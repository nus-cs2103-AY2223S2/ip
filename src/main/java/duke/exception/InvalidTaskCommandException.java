package duke.exception;

/**
 * InvalidTaskException class handles task that are invalid
 */
public class InvalidTaskCommandException extends DukeException {
    public InvalidTaskCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
