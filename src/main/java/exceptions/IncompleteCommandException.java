package exceptions;

public class IncompleteCommandException extends DukeException {
    public IncompleteCommandException(String errorMessage, Throwable error) {
        super(errorMessage,error);
    }
}
