package duke.exception;

/**
 * An exception class that encapsulates the exceptions arisen from File operation.
 */
public class StorageFileException extends DukeException {
    /**
     * Constructor for DukeException with the error message to be printed.
     *
     * @param errorMessage The error message to be printed
     */
    public StorageFileException(String errorMessage) {
        super(errorMessage);
    }
}
