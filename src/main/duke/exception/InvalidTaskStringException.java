package exception;

public class InvalidTaskStringException extends DukeException {

    public InvalidTaskStringException(String errorMessage) {
        super(errorMessage);
    }
}