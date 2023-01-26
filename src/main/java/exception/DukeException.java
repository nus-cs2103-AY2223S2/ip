package exception;

/**
 * Represents an exception unique to the Duke application.
 */
public class DukeException extends Exception{
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
