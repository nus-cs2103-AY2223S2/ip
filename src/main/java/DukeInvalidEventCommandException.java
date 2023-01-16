public class DukeInvalidEventCommandException extends DukeException {

    DukeInvalidEventCommandException() {
        super("Usage: event <task name> /from <start> /to <end>");
    }
}
