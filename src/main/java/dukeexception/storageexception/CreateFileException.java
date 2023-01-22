package dukeexception.storageexception;

/**
 * Exception for handling file creation errors.
 */
public class CreateFileException extends StorageException {
    /**
     * Constructor for CreateFileException.
     */
    public CreateFileException() {
        super("Create file FAILURE!");
    }
}
