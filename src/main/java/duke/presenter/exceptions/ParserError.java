package duke.presenter.exceptions;

import duke.exceptions.PresenterException;

public class ParserError extends PresenterException {
    public ParserError(String errorMessage) {
        super("Parser error: " + errorMessage);
    }
}
