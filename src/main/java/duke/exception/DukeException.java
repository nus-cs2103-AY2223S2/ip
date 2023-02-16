package duke.exception;

/**
 * DukeException
 */
public class DukeException extends Exception {

    /**
     * Constructs a general duke exception.
     * @param errorMessage a message describing the exception
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String getMessage() {
        return "😢 OOPS!!! \n" + super.getMessage();
    }
}
