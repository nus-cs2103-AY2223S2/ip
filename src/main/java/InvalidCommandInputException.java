public class InvalidCommandInputException extends DukeException {
    String command;

    InvalidCommandInputException(String errorMessage, String command) {
        super(errorMessage);
        this.command = command;
    }
}
