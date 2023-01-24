package duke.exceptions;

public class DukeInvalidEventCommandException extends DukeException {

    public DukeInvalidEventCommandException() {
        super("Usage: event <task name> /from <start> /to <end>");
    }
}
