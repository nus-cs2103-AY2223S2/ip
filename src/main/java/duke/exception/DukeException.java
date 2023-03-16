package duke.exception;

/**
 * DukeException class that handles unique exceptions created by the Duke program
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    /**
     * Creates string representation of a DukeException
     *
     * @return the string representation of a DukeException
     */
    @Override
    public String toString() {
        return "OOPS!!! " + getMessage();
    }
}
