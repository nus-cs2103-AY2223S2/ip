package dukeexception.storageexception;

import dukeexception.DukeException;

/**
 * Exception for handling Storage-related problems.
 */
public class StorageException extends DukeException {
    /**
     * Constructor for StorageException.
     * @param message Message to be displayed.
     */
    public StorageException(String message) {
        super(message, null);
    }
}
