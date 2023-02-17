package exceptions;

/**
 * Mother of all Exceptions for duke
 */
public class DukeException extends Exception {

    protected static final String OOPS = "OOPS!!!";

    /**
     * This method constructs to pass on forward to Exception
     * @param msg Message to pass to Exception
     */
    public DukeException(String msg) {
        super(msg);
    }
}
