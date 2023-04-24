package twofive.exception;

/**
 * Represents an error encountered when a task number is not provided for parsing
 * when marking a task as done or not done, or when deleting it.
 */
public class EmptyTasknumException extends TwoFiveException {
    @Override
    public String getMessage() {
        return "Oops! TwoFive would like to remind you that the task number cannot be empty.";
    }
}
