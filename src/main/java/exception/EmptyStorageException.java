package exception;

public class EmptyStorageException extends DukeException {

    public EmptyStorageException (String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "No existing data, creating a new one now";
    }
}
