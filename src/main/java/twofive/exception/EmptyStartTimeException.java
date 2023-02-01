package twofive.exception;

/**
 * Represents an error encountered when a start time is not provided for parsing
 * when adding an Event task.
 */
public class EmptyStartTimeException extends TwoFiveException {
    @Override
    public String getMessage() {
        return ":( OOPS!!! The start time of a event cannot be empty.";
    }
}
