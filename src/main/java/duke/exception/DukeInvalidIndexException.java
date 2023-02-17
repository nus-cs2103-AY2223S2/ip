package duke.exception;

public class DukeInvalidIndexException extends DukeException {
    private static final String INVALID_DATE_FORMAT_MESSAGE = "    OOPS!!! Index out of bounds!!";

    public DukeInvalidIndexException() {
        super(INVALID_DATE_FORMAT_MESSAGE);
    }
}
