package errors;

public class InsufficientDeadlineArgumentException extends DukeRuntimeException{
    public InsufficientDeadlineArgumentException(String message) {
        super(message);
    }
}
