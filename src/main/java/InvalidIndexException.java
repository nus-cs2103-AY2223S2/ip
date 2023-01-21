public class InvalidIndexException extends DukeException {
    public InvalidIndexException(int idx) {
        super(String.format("Index of %d is out of bounds!", idx));
    }
}