package command.exceptions;

import exceptions.CommandException;

public class InvalidParameterError extends CommandException {
    public InvalidParameterError(String errorMessage) {
        super(errorMessage);
    }
}
