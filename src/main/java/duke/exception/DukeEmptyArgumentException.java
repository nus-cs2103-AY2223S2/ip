package duke.exception;

/**
 * Exception that occur in Duke to indicate user passing an empty argument for the command.
 */
public class DukeEmptyArgumentException extends DukeException {
    public DukeEmptyArgumentException(String msg) {
        super(msg);
    }
}
