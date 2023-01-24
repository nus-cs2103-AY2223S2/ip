package exceptions;

/**
 * Exception for when Duke encounters a command which receives an invalid index.
 * Index received could be a non-positive integer, or types other than an integer.
 * Index could be out of range of task list too.
 */
public class InvalidIndexException extends DukeException {
    public InvalidIndexException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
