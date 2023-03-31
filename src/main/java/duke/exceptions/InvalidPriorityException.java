package duke.exceptions;

/**
 * Exception thrown when user supplies an invalid priority string.
 */
public class InvalidPriorityException extends DukeException {
    /**
     * Constructor for an InvalidPriorityException.
     */
    public InvalidPriorityException() {
        super("Not a valid priority da yo!!!");
    }
}
