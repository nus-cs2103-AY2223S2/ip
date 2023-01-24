package exceptions;

/**
 * Exception for when Duke encounters a command to operate on a task which does not exist.
 */
public class NoSuchTaskException extends DukeException {
    public NoSuchTaskException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
