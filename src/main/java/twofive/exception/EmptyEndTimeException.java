package twofive.exception;

/**
 * Represents an error encountered when an end time is not provided for parsing
 * when adding an Event task.
 */
public class EmptyEndTimeException extends TwoFiveException {
    @Override
    public String getMessage() {
        return "Oops! TwoFive would like to remind you that the end time of an event task cannot be empty.";
    }
}
