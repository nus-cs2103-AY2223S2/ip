package twofive.exception;

/**
 * Represents an error encountered when a task cannot be found given a number.
 */
public class InvalidTaskException extends TwoFiveException {
    @Override
    public String getMessage() {
        return ":( OOPS!!! A task with the given number cannot be found.";
    }
}
