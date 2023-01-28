package command.exceptions;

import exceptions.CommandException;

public class CommandExecutionError extends CommandException {
    public CommandExecutionError(String errorMessage) {
        super(errorMessage);
    }
}
