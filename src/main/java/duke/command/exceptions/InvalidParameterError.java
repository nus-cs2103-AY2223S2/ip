package duke.command.exceptions;

import duke.exceptions.CommandException;

/**
 * A class for error when invalid parameter(s) are passed to a command.
 */
public class InvalidParameterError extends CommandException {
    /**
     * Instantiate an InvalidParameterError with a specific error message.
     * @param errorMessage: the error message to be displayed to the user.
     */
    public InvalidParameterError(String errorMessage) {
        super(errorMessage);
    }
}
