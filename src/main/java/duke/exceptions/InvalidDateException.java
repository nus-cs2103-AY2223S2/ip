package duke.exceptions;

/**
 * InvalidDateException represents an exception if the date entered
 * for event or deadline is unrecognised.
 */
public class InvalidDateException extends Exception {

    /**
     * Creates a InvalidDateException
     */
    public InvalidDateException() {
        super("OOPS!!! Please key the date in the format: yyyy-mm-dd");
    }
}
