package duke.exception;

/**
 * A class to represent an invalid date exception.
 */
public class InvalidDateFormatException extends Error {
    /**
     * Constructor for InvalidDateFormatException.
     *
     * @param dateString
     */
    public InvalidDateFormatException(String dateString) {
        super(String.format(
                "☹ OOPS!!! I don't understand what %s means, please make sure your date is in yyyy-MM-dd format",
                dateString));
    }
}
