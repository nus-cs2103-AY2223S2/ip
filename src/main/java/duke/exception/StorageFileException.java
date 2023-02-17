package duke.exception;

/**
 * An exception class that encapsulates the exceptions arisen from File operation.
 */
public class StorageFileException extends DukeException {
    private static final String STORAGE_ERROR_MESSAGE = "There is something wrong when handling the file ...\n";
    /**
     * Constructor for DukeException with the error message to be printed.
     *
     * @param errorMessage The error message to be printed
     */
    public StorageFileException(String errorMessage) {
        super(STORAGE_ERROR_MESSAGE + errorMessage);
    }
}
