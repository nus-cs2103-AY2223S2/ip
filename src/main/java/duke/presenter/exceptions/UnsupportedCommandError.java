package duke.presenter.exceptions;

public class UnsupportedCommandError extends ParserError {
    private static String errorMessage = "This duke.command has not been implemented yet";
    public UnsupportedCommandError() {
        super(errorMessage);
    }
}
