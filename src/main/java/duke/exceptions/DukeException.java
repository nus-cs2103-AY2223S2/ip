package duke.exceptions;

/**
 * The {@code DukeException} class represents a general exception that
 * is thrown when an error occurs during Duke's execution.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
