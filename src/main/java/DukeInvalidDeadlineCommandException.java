public class DukeInvalidDeadlineCommandException extends DukeException {

    DukeInvalidDeadlineCommandException() {
        super("Usage: deadline <task name> /by <deadline>");
    }
}
