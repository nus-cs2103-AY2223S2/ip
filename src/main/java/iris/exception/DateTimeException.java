package iris.exception;

/**
 * encountered when there is a problem parsing a date-time string
 */
public class DateTimeException extends IrisException {
    public DateTimeException() {
        super("Date/time cannot be understood." );
    }
}
