public class InvalidCommandArgException extends DukeException {
    String arg;
    String command;

    InvalidCommandArgException(String errorMessage, String command, String arg) {
        super(errorMessage);
        this.command = command;
        this.arg = arg;
    }
}
