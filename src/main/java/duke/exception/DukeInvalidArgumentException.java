package duke.exception;

/**
 * Throw when user enter invalid argument for the command.
 */
public class DukeInvalidArgumentException extends DukeException {
    public DukeInvalidArgumentException(String msg) {
        super(msg);
    }
}
