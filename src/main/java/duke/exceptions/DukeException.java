package duke.exceptions;

/**
 * Represents an exception generated during Duke's operations.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
