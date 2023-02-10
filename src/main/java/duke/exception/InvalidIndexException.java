package duke.exception;

/**
 * Represents Invalid index exception
 */
public class InvalidIndexException extends DukeException {
    public InvalidIndexException() {
        super("WOOF! The indexing is not valid!");
    }
}

