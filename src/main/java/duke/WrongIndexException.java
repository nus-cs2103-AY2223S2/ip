package duke;

public class WrongIndexException extends DukeException {
    public WrongIndexException() {
        super("OOPS!!! The index cannot be out of bounds.");
    }
}
