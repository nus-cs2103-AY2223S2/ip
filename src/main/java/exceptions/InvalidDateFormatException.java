package exceptions;

/**
 * This class is used to throw an exception when an the date given is not in the correct format.
 */
public class InvalidDateFormatException extends TaskGenieException {
    /**
     * Constructor for the InvalidDateFormatException.
     * @param err The error.
     */
    public InvalidDateFormatException(Throwable err) {
        super("Please use the following format for dates: YYYY-MM-DD HHMM", err);
    }
}
