package clippy.exception;

/**
 * A complaint that the Event start/end times cannot be recognised.
 *
 * @author chunzkok
 */
public class ClippyInvalidEventException extends ClippyException {
    /**
     * Creates a ClippyInvalidEventException that contains a message
     * on how to specify the event start and end times correctly.
     */
    public ClippyInvalidEventException() {
        super("cannot recognise event start and end time - please specify it as such: \n"
                + "event <description> /from <start> /to <end>");
    }
}
