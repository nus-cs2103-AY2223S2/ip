package duke.exceptions;

public class DukeInvalidUnmarkCommandException extends DukeException {

    public DukeInvalidUnmarkCommandException() {
        super("Usage: unmark <task no.>");
    }
}
