package duke.exceptions;

/**
 * This is a class that covers all exceptions that might appear in Duke
 */
public abstract class DukeException extends RuntimeException {

    /**
     * Constructor for DukeException class
     *
     * @param errorMessage the error message of the exception thrown
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
