public class DukeInvalidDeadlineCommandException extends DukeException {

    DukeInvalidDeadlineCommandException() {
        super("Usage: deadline <task name> /by <deadline>\nExample: deadline buy groceries /by 2012-12-31");
    }
}
