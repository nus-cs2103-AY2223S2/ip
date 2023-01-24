package twofive.exception;

/**
 * Represents an error encountered when a deadline is not provided for parsing
 * when adding a Deadline task.
 */
public class EmptyDeadlineException extends TwoFiveException {
    @Override
    public String getMessage() {
        return ":( OOPS!!! The deadline of a deadline cannot be empty.";
    }
}
