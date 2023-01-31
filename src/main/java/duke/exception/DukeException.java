package duke.exception;

/**
 * A custom exception for the Duke program.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
