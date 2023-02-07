package exception;

/**
 * Represents the exception when a command receives an invalid input.
 */
public class InvalidCommandInputException extends DukeException {

    private String command;

    /**
     * Constructor for InvalidCommandInputException.
     * @param errorMessage Message for the error.
     * @param command The command that triggered the error.
     */
    public InvalidCommandInputException(String errorMessage, String command) {
        super(errorMessage);
        this.command = command;
    }

    /**
     * Accessor method to get command.
     * @return Command that caused the exception.
     */
    public String getCommand() {
        return command;
    }
}