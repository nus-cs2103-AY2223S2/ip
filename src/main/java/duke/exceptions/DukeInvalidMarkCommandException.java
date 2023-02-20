package duke.exceptions;

public class DukeInvalidMarkCommandException extends DukeException {

    public DukeInvalidMarkCommandException() {
        super("Usage: mark <task no.>");
    }
}
