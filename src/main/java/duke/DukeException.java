package duke;

/**
 * Unchecked Exception thrown when supplying invalid command to Duke.
 */
public class DukeException extends RuntimeException {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "OOPS!!! " + getMessage();
    }
}
