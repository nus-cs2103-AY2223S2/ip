package duke.dukeexception;

/**
 * This class handles exceptions pertaining to Duke.
 */
public class DukeException extends Exception {
    /**
     * Creates a DukeException object.
     *
     * @param message The error message of this exception.
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
