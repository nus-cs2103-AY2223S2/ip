package duke.exception;

public class UnknownCommandError extends DukeException {
    public UnknownCommandError(String err) {
        super(err);
    }
}
