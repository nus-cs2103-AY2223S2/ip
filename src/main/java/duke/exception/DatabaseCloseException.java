package duke.exception;

/**
 * DatabaseCloseException
 */
public class DatabaseCloseException extends DukeException {

    /**
     * Default constructor.
     * @param errorMessage
     */
    public DatabaseCloseException(String errorMessage) {
        super(errorMessage);
    }
}