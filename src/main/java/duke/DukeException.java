package duke;

/**
 * Exception class, thrown by duke.Duke
 */
public class DukeException extends Exception {
    protected String errorMessage;

    /**
     * Constructor
     *
     * @param errorMessage: the error message of the exception
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * Returns the error message as a string
     *
     * @return the string error message
     */
    @Override
    public String toString() {
        return this.errorMessage;
    }
}
