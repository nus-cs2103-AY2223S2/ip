package exceptions;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
