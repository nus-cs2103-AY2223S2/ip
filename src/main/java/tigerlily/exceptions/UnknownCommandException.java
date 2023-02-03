package tigerlily.exceptions;

public class UnknownCommandException extends DukeExceptions {
    private static final String ERROR_MESSAGE = "⚠ oops...we don't know what that means, please try again\n";
    public UnknownCommandException() {
        super(ERROR_MESSAGE);
    }
}