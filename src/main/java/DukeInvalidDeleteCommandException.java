public class DukeInvalidDeleteCommandException extends DukeException {

    DukeInvalidDeleteCommandException() {
        super("Usage: delete <task no.>");
    }
}
