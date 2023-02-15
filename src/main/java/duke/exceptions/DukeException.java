package duke.exceptions;

/**
 * An Exception signifying the complaints of Duke.
 *
 * @author jengoc415
 */
public class DukeException extends Exception {
    /**
     * Creates a DukeException instance.
     *
     * @param msg The message to be contained in this DukeException.
     */
    public DukeException(String msg) {
        super(msg);
    }
}
