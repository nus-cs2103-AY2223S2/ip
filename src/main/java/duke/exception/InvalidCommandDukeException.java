package duke.exception;

public class InvalidCommandDukeException extends DukeException {
    private static final String ERROR_MESSAGE = "Invalid command!";

    public InvalidCommandDukeException() {
        super(ERROR_MESSAGE);
    }
}
