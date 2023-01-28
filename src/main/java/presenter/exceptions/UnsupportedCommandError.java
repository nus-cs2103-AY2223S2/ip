package presenter.exceptions;

public class UnsupportedCommandError extends ParserError {
    private static String errorMessage = "This command has not been implemented yet";
    public UnsupportedCommandError() {
        super(errorMessage);
    }
}
