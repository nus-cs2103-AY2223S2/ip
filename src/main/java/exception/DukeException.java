package exception;

/**
 * All custom exceptions used by Duke will extend this DukeException
 */
public class DukeException extends RuntimeException {
    public DukeException(String errMsg) {
        super(errMsg);
    }

    /**
     * Customised string for the error message
     * @return error message
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! " + super.getMessage();
    }
}
