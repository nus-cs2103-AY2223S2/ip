package duke.exceptions;

public class DukeEmptyUndoHistoryException extends DukeException {

    public DukeEmptyUndoHistoryException() {
        super("There is nothing to undo.");
    }
}
