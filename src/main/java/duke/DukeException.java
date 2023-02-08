package duke;

/**
 * Exception class for Duke methods to throw.
 */
public class DukeException extends Exception {
    /**
     * Exception with custom error message.
     *
     * @param errorMessage Custom error message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return ":( OOPS!!! " + super.getMessage();
    }
}
