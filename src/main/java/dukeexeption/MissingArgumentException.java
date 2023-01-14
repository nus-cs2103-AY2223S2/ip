package dukeexeption;

public class MissingArgumentException extends DukeException {
    public MissingArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
