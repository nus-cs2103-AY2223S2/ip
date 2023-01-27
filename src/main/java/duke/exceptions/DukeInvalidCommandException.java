package duke.exceptions;

public class DukeInvalidCommandException extends DukeException {
    protected static final String INVALID_COMMAND_MESSAGE = "I'm sorry, but I don't know what that means :-(";

    public DukeInvalidCommandException() {
        super(DukeInvalidCommandException.INVALID_COMMAND_MESSAGE);
    }
}