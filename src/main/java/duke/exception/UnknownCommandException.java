package duke.exception;

/**
 * An exception when a command isn't valid.
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException(String message) {
        super(message);
    }
}
