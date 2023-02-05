package duke;

/**
 * Represents an exception thrown by Duke.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns the error message of the exception.
     * @return String representing error message.
     */
    @Override
    public String toString() {
        return this.getMessage();
    }
}
