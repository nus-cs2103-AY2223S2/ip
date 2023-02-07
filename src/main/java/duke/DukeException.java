package duke;

/**
 * Exception class.
 */
public class DukeException extends Exception {
    protected String errorMsg;

    /**
     * Constructor.
     *
     * @param errorMsg The error message of the exception.
     */
    public DukeException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    /**
     * Returns the error message.
     *
     * @return The error message.
     */
    @Override
    public String toString() {
        return this.errorMsg;
    }
}
