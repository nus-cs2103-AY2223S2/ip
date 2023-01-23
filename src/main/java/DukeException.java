/**
 * Exception class, thrown by Duke
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

    @Override
    public String toString() {
        return this.errorMessage;
    }
}
