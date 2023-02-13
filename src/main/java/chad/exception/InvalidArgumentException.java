package chad.exception;

/**
 * Exception when user inserted invalid argument for specific command.
 */
public class InvalidArgumentException extends DukeException {
    public InvalidArgumentException(String msg) {
        super(msg);
    }
}
