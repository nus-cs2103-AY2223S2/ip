package duke.exception;

public class InvalidTaskNumberException extends DukeException{
    public InvalidTaskNumberException() {
        super("Index out of bounds!");
    }
}
