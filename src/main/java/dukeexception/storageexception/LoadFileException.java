package dukeexception.storageexception;

/**
 * Exception for handling failed file load.
 */
public class LoadFileException extends StorageException {
    public LoadFileException() {
        super("Load file FAILURE!");
    }
}
