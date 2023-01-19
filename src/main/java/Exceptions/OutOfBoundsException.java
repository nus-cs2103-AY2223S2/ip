package Exceptions;

public class OutOfBoundsException extends DukeException {
    public OutOfBoundsException(String e) {
        super("Hey! The value you chose is out of bounds!");
    }
}
