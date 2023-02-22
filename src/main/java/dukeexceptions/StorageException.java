package dukeexceptions;

/**
 * An exception that is thrown when there is an issue with a <code>Storage</code> object.
 */
public class StorageException extends DukeException {

    public StorageException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "There seems to be a problem with the Storage file..." + getMessage();
    }
}
