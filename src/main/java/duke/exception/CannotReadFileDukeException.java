package duke.exception;

public class CannotReadFileDukeException extends DukeException {
    private static final String ERROR_MESSAGE = "Cannot read file!";

    public CannotReadFileDukeException() {
        super(ERROR_MESSAGE);
    }
}
