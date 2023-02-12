package duke;

/**
 * Represents exceptions encountered during execution of the programme
 */
public class DukeException extends Exception {

    /**
     * Class constructor
     * @param message Error message received.
     */
    public DukeException(String message) {
        super(message);
    }
}
