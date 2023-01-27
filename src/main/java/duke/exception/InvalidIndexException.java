package duke.exception;

public class InvalidIndexException extends DukeException {
    public InvalidIndexException() {
        super("OOPS! The indexing is not valid!");
    }
}