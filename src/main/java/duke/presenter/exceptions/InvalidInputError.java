package duke.presenter.exceptions;

public class InvalidInputError extends ParserError {
    private static final String genericParserError = "Oops! Invalid input";
    public InvalidInputError() {
        super(genericParserError);
    }
    public InvalidInputError(String errorMessage) {
        super(errorMessage);
    }
}
