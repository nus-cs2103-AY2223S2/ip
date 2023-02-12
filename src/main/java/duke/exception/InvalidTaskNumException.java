package duke.exception;

public class InvalidTaskNumException extends DukeException{
    public InvalidTaskNumException() {
        super("Index out of bounds!");
    }
}
