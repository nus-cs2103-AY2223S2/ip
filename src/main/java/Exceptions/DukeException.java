package Exceptions;

/**
 * This class is used to throw an exception when running Duke.
 */
public abstract class DukeException extends RuntimeException {
    /**
     * Constructor for the DukeException.
     * @param errorMessage The error message to be printed.
     * @param err The error.
     */
    public DukeException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
