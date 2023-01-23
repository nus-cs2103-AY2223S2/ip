public class EmptyCommandException extends DukeException {
    String command;

    EmptyCommandException(String errorMessage, String command) {
        super(errorMessage);
        this.command = command;
    }
}
