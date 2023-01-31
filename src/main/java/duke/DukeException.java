package duke;

/**
 * Represents a duke exception.
 */
public class DukeException
        extends RuntimeException {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
