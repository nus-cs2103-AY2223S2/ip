package duke.exception;

/**
 * DatabaseCloseException.
 *
 * @see DukeException
 */
public class DatabaseCloseException extends DukeException {

    /**
     * {@inheritDoc}
     */
    public DatabaseCloseException(String errorMessage) {
        super(errorMessage);
    }
}
