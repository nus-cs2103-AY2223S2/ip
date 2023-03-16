package duke.exception;

/**
 * InvalidTaskException class handles command that are invalid
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
