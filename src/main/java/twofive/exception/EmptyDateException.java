package twofive.exception;

/**
 * Represents an error encountered when a date is not provided for parsing
 * in a DueCommand.
 */
public class EmptyDateException extends TwoFiveException {
    @Override
    public String getMessage() {
        return "Oops! TwoFive would like to remind you that the due date cannot be empty.";
    }
}
