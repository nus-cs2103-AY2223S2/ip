package duke.exception;

/**
 * Indicates that a save file cannot be read.
 */
public class CannotReadFileDukeException extends DukeException {
    public static final String ERROR_MESSAGE = "Cannot read file!";

    /**
     * Creates an exception with the error message.
     */
    public CannotReadFileDukeException() {
        super(ERROR_MESSAGE);
    }
}
