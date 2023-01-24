package twofive.exception;

/**
 * Represents an error encountered when an end time is not provided for parsing
 * when adding an Event task.
 */
public class EmptyEndTimeException extends TwoFiveException {
    @Override
    public String getMessage() {
        return ":( OOPS!!! The end time of a event cannot be empty.";
    }
}
