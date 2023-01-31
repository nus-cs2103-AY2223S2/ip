package duke;

public class InvalidIndexException extends IllegalArgumentException {
    public InvalidIndexException(String message) {
        super(message);
    }

    public InvalidIndexException() {
        super();
    }
}
