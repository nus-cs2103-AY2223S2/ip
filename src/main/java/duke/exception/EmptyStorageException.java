package duke.exception;

/**
 * Command when there isn't an existing data.
 */
public class EmptyStorageException extends DukeException {

    public EmptyStorageException (String message) {
        super(message);
    }

}
