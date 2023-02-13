package duke.exceptions;

public class StorageException extends DukeException {
    public StorageException() {
        super("Your data cannot be accessed.\n");
    }
}
