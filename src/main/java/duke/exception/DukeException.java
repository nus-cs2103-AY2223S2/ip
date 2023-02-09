package duke.exception;

/**
 * Class to represent exceptions specific to Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructor to create a DukeException.
     *
     * @param str Error message.
     */
    public DukeException(String str) {
        super(str);
    }
}
