package duke.exceptions;

public abstract class DukeException extends RuntimeException {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
