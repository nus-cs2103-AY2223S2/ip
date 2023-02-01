package lele.exception;

/**
 * Command when there isn't an existing data.
 */
public class EmptyStorageException extends LeleException {

    public EmptyStorageException(String message) {
        super(message);
    }

}
