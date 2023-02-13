package duke.exception;

/**
 * Exception that occur in Duke to indicate user passing an empty argument for the command.
 */
public class DukeEventOverlapException extends DukeException {
    public DukeEventOverlapException(String msg) {
        super(msg);
    }
}
