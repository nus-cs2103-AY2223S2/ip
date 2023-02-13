package chad.exception;

/**
 * Exception class to handle invalid date time format.
 */
public class InvalidDateTimeException extends DukeException {
    /**
     * Constructor to call the super class with defined error message.
     */
    public InvalidDateTimeException() {
        super("Please insert your date using the format, YYYY-MM-DD (e.g. 2000-01-01 2311)");
    }
}
