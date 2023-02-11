package duke.presenter.exceptions;

import duke.exceptions.PresenterException;

/**
 * Exception that occurs when there is an error InputParser.
 */
public class ParserError extends PresenterException {
    public ParserError(String errorMessage) {
        super("Parser error: " + errorMessage);
    }
}
