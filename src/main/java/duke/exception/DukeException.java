package duke.exception;

/**
 * Exception that is specific to the Duke program.
 *
 * @author Oskar Lew
 * @version 0.1
 * @since 0.1
 */
public class DukeException extends RuntimeException {

    /**
     * Constructor for Duke Exception.
     * @param error Error message
     * @param err Contains the execution stack
     */
    public DukeException(String error, Throwable err) {
        super(error, err);
    }

}
