package duke.command.exceptions;

import duke.exceptions.CommandException;

public class InvalidParameterError extends CommandException {
    public InvalidParameterError(String errorMessage) {
        super(errorMessage);
    }
}
