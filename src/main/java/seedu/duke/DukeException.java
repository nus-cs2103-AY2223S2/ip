package seedu.duke;

public class DukeException extends Exception {
    private final String errorMessage;

    /**
     *  Constructor for DukeException
     *
     *  @param errorMessage Message to be printed out when exception occurs
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     *  Gets the error message for the exception faced
     *
     *  @return Reason for error faced
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
