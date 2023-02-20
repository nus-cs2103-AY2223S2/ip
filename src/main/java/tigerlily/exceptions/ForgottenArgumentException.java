package tigerlily.exceptions;

public class ForgottenArgumentException extends DukeExceptions {
    private static final String ERROR_MESSAGE = "⚠ oops...seems like you forgot part of a command, please try again\n";
    public ForgottenArgumentException() {
        super(ERROR_MESSAGE);
    }
}