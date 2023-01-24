package exceptions;

/**
 * Mother of all Exceptions for duke
 */
public class DukeException extends Exception {

    /**
     * Constructor to pass on forward to Exception
     * @param msg Message to pass to Exception
     */
    public DukeException(String msg) {
        super(msg);
    }
    protected final static String OOPS = "☹ OOPS!!!";
    public static String OOPS() {
        return OOPS;
    }
}
