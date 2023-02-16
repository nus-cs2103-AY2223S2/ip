package duke.exception;

/**
 * A class that deals with all types of exceptions specific
 * to Duke.
 */
public abstract class DukeException extends Exception {
    private String errorMessage;

    /**
     * DukeException constructor.
     *
     * @param errorMessage Error message of exception.
     */
    public DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getMessage() {
        return errorMessage;
    }
}
