package duke.exceptions;

public class DukeInvalidDeleteCommandException extends DukeException {

    public DukeInvalidDeleteCommandException() {
        super("Usage: delete <task no.>");
    }
}
