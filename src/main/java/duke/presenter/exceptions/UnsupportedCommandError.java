package duke.presenter.exceptions;

/**
 * Exception generated when command entered by the user is not recognized.
 */
public class UnsupportedCommandError extends ParserError {
    private static String errorMessage = "This command has not been implemented yet";
    public UnsupportedCommandError() {
        super(errorMessage);
    }
}
