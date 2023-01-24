package duke.exception;

public class CannotWriteFileDukeException extends DukeException {
    public static final String ERROR_MESSAGE = "Cannot write to file!";

    public CannotWriteFileDukeException() {
        super(ERROR_MESSAGE);
    }
}
