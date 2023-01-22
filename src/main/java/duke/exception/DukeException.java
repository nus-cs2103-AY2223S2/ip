package duke.exception;

/**
 * DukeException
 */
public class DukeException extends Exception {

    /**
     * Default constructor.
     * @param errorMessage
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! " + super.getMessage();
    }
}
