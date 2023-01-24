package duke.exception;

/**
 * Constructs a new exception representing an invalid deadline task input.
 */
public class InvalidDeadlineException extends Exception {

    public InvalidDeadlineException(String message) {
        super(message);
    }
}

