package duke.exceptions;

public class DukeInvalidFindCommandException extends DukeException {

    public DukeInvalidFindCommandException() {
        super("Usage: find <task name>");
    }
}
