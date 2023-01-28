package duke;

/**
 * Represents exceptions specific to Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException.
     *
     * @param message Error message for the exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns the details of the DukeException.
     *
     * @return Details of the DukeException.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
