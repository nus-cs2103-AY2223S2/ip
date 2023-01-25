package exception;

/**
 * Represents the exception when a command receives an invalid input.
 */
public class InvalidCommandInputException extends DukeException {
    String command;

    public InvalidCommandInputException(String errorMessage, String command) {
        super(errorMessage);
        this.command = command;
    }
}
