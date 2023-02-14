package duke.exception;

/**
 * NoSuchCommandException
 *
 * @see DukeException
 */
public class NoSuchCommandException extends DukeException {

    /**
     * {@inheritDoc}
     */
    public NoSuchCommandException(String errorMessage) {
        super(errorMessage);
    }
}
