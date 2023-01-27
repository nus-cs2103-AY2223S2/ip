package duke.exception;

public class EmptyDescException extends DukeException {
    String taskType;
    public EmptyDescException(String message) {
        super(message);
    }
}
