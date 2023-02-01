package twofive.exception;

/**
 * Represents an error encountered when a task that is not yet done
 * is being marked as not done again.
 */
public class TaskUndoneException extends TwoFiveException {
    @Override
    public String getMessage() {
        return ":( OOPS!!! This task has not been done yet.";
    }
}
