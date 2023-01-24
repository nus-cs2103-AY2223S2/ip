package exception;

public class InvalidCommandInputException extends DukeException {
    String command;

    public InvalidCommandInputException(String errorMessage, String command) {
        super(errorMessage);
        this.command = command;
    }
}
