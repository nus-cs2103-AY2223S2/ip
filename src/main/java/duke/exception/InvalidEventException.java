package duke.exception;

/**
 * Constructs a new exception representing an invalid event task input.
 */
public class InvalidEventException extends Exception {

    public InvalidEventException(String message) {
        super(message);
    }
}

