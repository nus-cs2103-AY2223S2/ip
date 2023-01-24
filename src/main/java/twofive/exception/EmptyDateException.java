package twofive.exception;

/**
 * Represents an error encountered when a date is not provided for parsing
 * in a DueCommand.
 */
public class EmptyDateException extends TwoFiveException {
    @Override
    public String getMessage() {
        return ":( OOPS!!! The due date cannot be empty.";
    }
}
