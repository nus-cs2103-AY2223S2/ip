package dukeexception.storageexception;

/**
 * Exception for handling a failed file creation.
 */
public class CreateFileException extends StorageException {
    public CreateFileException() {
        super("Create file FAILURE!");
    }
}
