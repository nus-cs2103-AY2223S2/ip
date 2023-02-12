package duke.exception;

/**
 * Indicates that the save file cannot be written to.
 */
public class CannotWriteFileDukeException extends DukeException {
    private static final String ERROR_MESSAGE = "Cannot write to file!";

    /**
     * Creates an exception with the error message.
     */
    public CannotWriteFileDukeException() {
        super(ERROR_MESSAGE);
    }
}
