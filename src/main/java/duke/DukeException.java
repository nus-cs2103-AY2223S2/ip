package duke;

/**
 * DukeException is a custom exception to encapsulates any errors faced during its execution.
 * @author Junyi
 */
public class DukeException extends Exception {
    private final String message;

    /**
     * Constructor for duke.DukeException.
     * @param message A string that describes the error.
     */
    public DukeException(String message) {
        this.message = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%s", message);
    }
}
