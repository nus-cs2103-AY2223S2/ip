package duke.exception;

/**
 * Exceptions that are specific to Duke.
 */
public class DukeException extends Exception {
    private String message;
    /**
     * Constructor for DukeException.
     *
     * @param message
     */
    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Returns the error message.
     *
     * @return String object
     */
    @Override
    public String getMessage() {
        return "OOPS! " + super.getMessage();
    }

    public String printError() {
        return this.message;
    }

}
