package duke;

/**
 * The class that extends Exception class.
 */
public class DukeException extends Exception {

    /**
     * DukeException constructor.
     *
     * @param error Takes in the error message.
     */
    public DukeException(String error) {
        super(error);
    }
}
