package duke.exceptions;

public class UnknownCommandException extends DukeExceptions {
    private static final String MESSAGE = "⚠ oops...we don't know what that means, please try again\n";
    public UnknownCommandException() {
        super(MESSAGE);
    }
}