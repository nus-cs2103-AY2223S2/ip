package duke;

/**
 * Handles exception for Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException class.
     *
     * @param msg Error message.
     */
    public DukeException(String msg) {
        super("☹ OOPS!!! " + msg + " ☹");
    }

}
