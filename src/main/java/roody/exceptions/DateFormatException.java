package roody.exceptions;

/**
 * Represents a custom date format exception
 */
public class DateFormatException extends RoodyException {
    public DateFormatException() {
        super("Accepted date format is yyyy-mm-dd.");
    }
}
