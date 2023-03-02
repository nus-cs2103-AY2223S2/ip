package chad.exception;

/**
 * Exception when index requested by the user is out of range.
 */
public class IndexOutOfBoundException extends DukeException {
    public IndexOutOfBoundException() {
        super("Index out of bound");
    }
}
