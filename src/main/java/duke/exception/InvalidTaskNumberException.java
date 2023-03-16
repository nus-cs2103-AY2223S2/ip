package duke.exception;

/**
 * InvalidTaskNumberException class handles wrong task number inputs
 */
public class InvalidTaskNumberException extends DukeException {
    public InvalidTaskNumberException() {
        super("Index out of bounds!");
    }
}
