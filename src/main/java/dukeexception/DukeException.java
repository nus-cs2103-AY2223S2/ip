package dukeexception;

/**
 * Exception for handling Duke-related problems.
 */
public class DukeException extends RuntimeException {
    /**
     * Constructor for DukeException.
     * @param errorMessage Error message.
     * @param err Throwable to be passed to parent constructor.
     */
    public DukeException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
