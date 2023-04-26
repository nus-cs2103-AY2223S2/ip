package exceptions;

/**
 * Exception thrown when the user inputs an invalid date or time.
 */
public class IllegalIndexException extends DukeException {
    public IllegalIndexException() {
        super("Index is out of bounds!");
    }
}
