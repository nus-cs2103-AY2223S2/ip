package duke.command.exceptions;

import duke.exceptions.CommandException;

public class CommandExecutionError extends CommandException {
    public CommandExecutionError(String errorMessage) {
        super(errorMessage);
    }
}
