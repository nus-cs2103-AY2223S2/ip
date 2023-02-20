package duke.presenter.exceptions;

/**
 * Exception generated when command entered by the user is not recognized.
 */
public class UnsupportedCommandError extends ParserError {
    private static final String ERROR_MESSAGE = "This command has not been implemented yet";
    public UnsupportedCommandError() {
        super(ERROR_MESSAGE);
    }
}
