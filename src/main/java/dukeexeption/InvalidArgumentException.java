package dukeexeption;

public class InvalidArgumentException extends DukeException {
    public InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
