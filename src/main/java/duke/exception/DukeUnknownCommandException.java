package duke.exception;

/**
 * Exception will be thrown when unknown command is given by the user.
 */
public class DukeUnknownCommandException extends DukeException {
    public DukeUnknownCommandException(String msg) {
        super(msg);
    }
}
