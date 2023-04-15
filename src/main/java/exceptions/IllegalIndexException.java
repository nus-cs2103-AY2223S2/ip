package exceptions;

public class IllegalIndexException extends DukeException {
    public IllegalIndexException() {
        super("Index is out of bounds!");
    }
}
