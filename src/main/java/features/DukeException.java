package features;

/**
 * General purpose Exception for use in Duke.
 */
public class DukeException extends Exception {
    protected String errorMessage;
    /**
     * Constructs DukeException instance.
     * @param errorMessage Message describing the error.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * Prints the error message associated with the DukeException.
     * @return The exception's error message.
     */
    public String printErrorMessage() {
        assert (!this.errorMessage.equals(""));
        return this.errorMessage;
    }

}
