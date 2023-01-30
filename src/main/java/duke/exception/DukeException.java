package duke.exception;

/**
 * The DukeException class implements exceptions specific to Duke.
 *
 * @author Chia Jeremy
 */
public class DukeException extends Exception {

    /**
     * Class constructor of DukeException.
     *
     * @param errorMessage the error message
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
