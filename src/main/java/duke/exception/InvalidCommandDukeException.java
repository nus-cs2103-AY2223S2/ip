package duke.exception;

public class InvalidCommandDukeException extends DukeException {
    public static final String ERROR_MESSAGE = "Invalid command!";

    public InvalidCommandDukeException() {
        super(ERROR_MESSAGE);
    }
}
