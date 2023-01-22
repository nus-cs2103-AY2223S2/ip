package dukeexception.storageexception;

import dukeexception.DukeException;

/**
 * Exception for handling storage-related problems.
 */
public class StorageException extends DukeException {
    public StorageException(String message) {
        super(message, null);
    }
}
