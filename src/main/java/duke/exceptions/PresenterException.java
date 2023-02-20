package duke.exceptions;

/**
 * The {@code PresenterException} class represents an exception that is thrown
 * when an error occurs in the presenter.
 */
public class PresenterException extends DukeException {
    public PresenterException(String errorMessage) {
        super(errorMessage);
    }
}
