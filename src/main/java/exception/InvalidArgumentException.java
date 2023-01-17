package exception;

/**
 * Exception to handle the case where the user input is invalid
 */
public class InvalidArgumentException extends DukeException {
    public InvalidArgumentException(String errMsg) {
        super(errMsg);
    }
}
