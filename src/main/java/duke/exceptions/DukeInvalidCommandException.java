package duke.exceptions;

/**
 * The exception that's thrown when an invalid base command is entered into Duke.
 * An example of a base command is the "unknowncommand" in
 * "unknowncommand DESCRIPTION /param PARAM VALUE".
 */
public class DukeInvalidCommandException extends DukeException {
    /** Default error message for invalid commands in Duke. */
    protected static final String INVALID_COMMAND_MESSAGE = "I'm sorry, but I don't know what that means :-(";

    public DukeInvalidCommandException() {
        super(DukeInvalidCommandException.INVALID_COMMAND_MESSAGE);
    }
}
