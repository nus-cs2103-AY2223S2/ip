package duke;

/**
 * Class that encapsulates all exceptions that are unique to duke.
 */
public class DukeException extends IllegalStateException {
    public DukeException(String message) {
        super(message);
    }
}
