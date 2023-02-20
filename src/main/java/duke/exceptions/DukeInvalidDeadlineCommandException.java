package duke.exceptions;

public class DukeInvalidDeadlineCommandException extends DukeException {

    public DukeInvalidDeadlineCommandException() {
        super(
                "Usage: deadline <task name> /by <deadline>\nExample: deadline buy groceries /by 2012-12-31");
    }
}
