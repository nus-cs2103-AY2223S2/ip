package duke.exception;

/**
 * InvalidCommandArgsException
 *
 * @see DukeException
 */
public class InvalidCommandArgsException extends DukeException {

    /**
     * {@inheritDoc}
     */
    public InvalidCommandArgsException(String errorMessage) {
        super(errorMessage);
    }
}
