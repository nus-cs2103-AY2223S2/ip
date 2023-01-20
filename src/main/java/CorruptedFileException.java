public class CorruptedFileException extends StorageException {
    public CorruptedFileException() {
        super("File Corrupted. FAILURE!");
    }
}
