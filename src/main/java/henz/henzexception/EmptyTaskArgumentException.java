package henz.henzexception;

/**
 * EmptyTaskArgumentException class extends DukeException class.
 */
public class EmptyTaskArgumentException extends HenzException {

    /**
     * Constructor.
     * @param message the error message
     */
    public EmptyTaskArgumentException(String message) {
        super(message);
    }
}
