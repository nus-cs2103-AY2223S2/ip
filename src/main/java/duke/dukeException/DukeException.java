package duke.dukeexception;

/**
 * A special exception used by the Duke class.
 */
public class DukeException extends Exception {

    /**
     * Constructor for the Duke.DukeException class.
     * @param error The string to be passed into
     *              the parent class' constructor.
     */
    public DukeException(String error) {
        super(error);
    }
}
