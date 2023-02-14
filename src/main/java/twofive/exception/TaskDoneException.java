package twofive.exception;

/**
 * Represents an error encountered when a task that is already done
 * is being marked as done again.
 */
public class TaskDoneException extends TwoFiveException {
    @Override
    public String getMessage() {
        return "Oops! TwoFive would like to remind you that this task has already been done.";
    }
}
