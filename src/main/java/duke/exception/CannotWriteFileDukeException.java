package duke.exception;

public class CannotWriteFileDukeException extends DukeException {
    private static final String ERROR_MESSAGE = "Cannot write to file!";

    public CannotWriteFileDukeException() {
        super(ERROR_MESSAGE);
    }
}
