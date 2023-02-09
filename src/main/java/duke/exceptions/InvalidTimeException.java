package duke.exceptions;

/**
 * InvalidTimeException represents an exception if
 * the format of event or deadline is unrecognised.
 */
public class InvalidTimeException extends Exception {

    /**
     * Create a InvalidTimeException
     */
    public InvalidTimeException() {
        super("OOPS!!! Please specify a time.");
    }
}
