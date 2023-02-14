package duke.exception;

/**
 * DatabaseNotFoundException.
 *
 * @see DukeException
 */
public class DatabaseNotFoundException extends DukeException {

    /**
     * {@inheritDoc}
     */
    public DatabaseNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}