package twofive.exception;

/**
 * Represents an error encountered when a deadline is not provided for parsing
 * when adding a Deadline task.
 */
public class EmptyDeadlineException extends TwoFiveException {
    @Override
    public String getMessage() {
        return "Oops! TwoFive would like to remind you that the deadline of a deadline task cannot be empty.";
    }
}
