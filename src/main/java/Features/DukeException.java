package Features;

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

    public String printErrorMessage() {
        return this.errorMessage;
    }

}
