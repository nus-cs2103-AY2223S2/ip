package duke.exception;

public class EmptyArgumentDukeException extends DukeException {
    private static final String ERROR_MESSAGE = "One or more arguments are empty!";

    public EmptyArgumentDukeException() {
        super(ERROR_MESSAGE);
    }
}
