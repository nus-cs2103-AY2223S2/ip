package duke.exception;

/**
 * DukeInvalidCommandException is a type of DukeException that is thrown
 * when there are errors in storing Duke chatbot application data.
 */
public class DukeStorageException extends DukeException {
    public DukeStorageException(String errorMessage) {
        super(errorMessage);
    }
}
