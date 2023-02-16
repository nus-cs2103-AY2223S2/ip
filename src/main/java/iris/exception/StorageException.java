package iris.exception;

/**
 * encountered when there is a problem while storing the tasks.
 */
public class StorageException extends IrisException {
    public StorageException(String m) {
        super("Something went wrong while creating the storing the file: " + m);
    }
}
