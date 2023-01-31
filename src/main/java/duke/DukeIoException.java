package duke;

/**
 * Throws when IOException happen in the duke.
 */
public class DukeIoException extends DukeException {
    public DukeIoException(String msg) {
        super(msg);
    }
}
