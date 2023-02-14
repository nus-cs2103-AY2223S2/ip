package twofive.exception;

/**
 * Represents an error encountered when a start time is not provided for parsing
 * when adding an Event task.
 */
public class EmptyStartTimeException extends TwoFiveException {
    @Override
    public String getMessage() {
        return "Oops! TwoFive would like to remind you that the start time of an event task cannot be empty.";
    }
}
