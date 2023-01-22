package dukeexception.storageexception;

/**
 * Exception for handling failed file parsing.
 */
public class CorruptedFileException extends StorageException {
    /**
     * Constructor for CorruptedFileException.
     */
    public CorruptedFileException() {
        super("File Corrupted. FAILURE!");
    }
}
