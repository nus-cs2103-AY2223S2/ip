package henz.henzexception;

/**
 * StorageException class exends from HenzException class.
 */
public class StorageException extends HenzException {

    /**
     * Constructor.
     * @param message the error message
     */
    public StorageException(String message) {
        super(message);
    }
}
