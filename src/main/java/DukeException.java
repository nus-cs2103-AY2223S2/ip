// Learn to create custom Exceptions: https://www.baeldung.com/java-new-custom-exception

/**
 * DukeException is the base class of all exceptions in the Duke application.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    public DukeException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
