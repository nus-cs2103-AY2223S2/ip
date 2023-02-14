package dukeexception.storageexception;

/**
 * Exception for handling failed file load.
 */
public class LoadFileException extends StorageException {
    /**
     * Constructor for LoadFileException.
     */
    public LoadFileException() {
        super("Load file FAILURE!");
    }
}
