package duke.presenter.exceptions;

/**
 * Exception that occurs when the user enters an invalid input.
 */
public class InvalidInputError extends ParserError {
    private static final String genericParserError = "Oops! Invalid input";

    /**
     * Instantiate an InvalidInputError exception with a generic error message.
     */
    public InvalidInputError() {
        super(genericParserError);
    }

    /**
     * Instantiate an InvalidInputError exception with the supplied error message.
     * @param errorMessage the error message to be displayed to the user.
     */
    public InvalidInputError(String errorMessage) {
        super(errorMessage);
    }
}
