package dukeexception.storageexception;

/**
 * Exception for handling a corrupted save file.
 */
public class CorruptedFileException extends StorageException {
    public CorruptedFileException() {
        super("File Corrupted. FAILURE!");
    }
}
