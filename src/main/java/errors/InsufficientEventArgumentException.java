package errors;

public class InsufficientEventArgumentException extends DukeRuntimeException{
    public InsufficientEventArgumentException(String message) {
        super(message);
    }
}
