package henz.henzexception;

/**
 * UnmarkIndexDoesNotExistException class extends from DukeException class.
 */
public class UnmarkIndexDoesNotExistException extends HenzException {

    /**
     * Constructor.
     * @param message the error message
     */
    public UnmarkIndexDoesNotExistException(String message) {
        super(message);
    }
}
