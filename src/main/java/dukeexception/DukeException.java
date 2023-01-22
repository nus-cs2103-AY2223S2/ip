package dukeexception;

/**
 * Exception for handling Duke-related problems.
 */
public class DukeException extends RuntimeException {
    public DukeException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
