package clippy.exception;

/**
 * A complaint that the deadline cannot be recognised.
 *
 * @author chunzkok
 */
public class ClippyMissingDeadlineException extends ClippyException {
    /**
     * Creates a ClippyMissingDeadlineException that contains a message
     * on how to specify the date correctly.
     */
    public ClippyMissingDeadlineException() {
        super("deadline cannot be recognised - please specify the deadline as such: \n"
                + "deadline <description> /by <deadline>");
    }
}
