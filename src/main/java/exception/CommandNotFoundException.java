package exception;

/**
 * Represents the exception where the command given is not valid.
 */
public class CommandNotFoundException extends DukeException {

    private String command;

    /**
     * Constructor for CommandNotFoundException.
     * @param errorMessage Message for the error.
     * @param command The command that could not be found.
     */
    public CommandNotFoundException(String errorMessage, String command) {
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