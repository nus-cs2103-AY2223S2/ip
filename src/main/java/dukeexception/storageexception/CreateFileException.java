package dukeexception.storageexception;

/**
 * Exception for handling a failed file creation.
 */
public class CreateFileException extends StorageException {
    /**
     * Constructor for CreateFileException.
     */
    public CreateFileException() {
        super("Create file FAILURE!");
    }
}
