public class EmptyCommandException extends DukeException {
    String arg;
    String command;

    EmptyCommandException(String errorMessage, String command) {
        super(errorMessage);
        this.command = command;
    }
}
