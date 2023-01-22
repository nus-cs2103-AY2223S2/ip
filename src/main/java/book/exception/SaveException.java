package book.exception;

/**
 * Subclass of {@code BookException} thrown when an error occurs loading to {@code TaskList} of
 * {@code Book} from {@code Storage}.
 */
public class SaveException extends BookException {
    /**
     * Initializes a {@code SaveException}.
     * @param message {@code String} message associated with the {@code SaveException}.
     */
    public SaveException(String message) {
        super(message);
    }
}
