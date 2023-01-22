package dukeexception.storageexception;

/**
 * Exception for handling failed file save.
 */
public class SaveFileException extends StorageException {
    public SaveFileException() {
        super("Save file FAILURE!");
    }
}
