package duke.exception;

/**
 * InvalidTaskException class handles task that are invalid
 */
public class InvalidTaskException extends DukeException {
    public InvalidTaskException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
