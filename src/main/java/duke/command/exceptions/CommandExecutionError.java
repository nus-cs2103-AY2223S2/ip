package duke.command.exceptions;

import duke.exceptions.CommandException;

/**
 * Class for error during command execution.
 */
public class CommandExecutionError extends CommandException {
    /**
     * Create an instance of CommandExecutionError with a specific error message.
     * @param errorMessage: the error message to be shown to the user.
     */
    public CommandExecutionError(String errorMessage) {
        super(errorMessage);
    }
}
