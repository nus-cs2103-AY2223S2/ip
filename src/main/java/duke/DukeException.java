package duke;

/**
 * DukeException is a custom Exception class representing Duke chatbot specific exceptions.
 *
 * @author      Tseng Chen-Yu
 * @version     %I%, %G%
 * @since       1.0
 */
public class DukeException extends Exception {
    public DukeException(String errMsg) {
        super(errMsg);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
