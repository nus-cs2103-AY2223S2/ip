package iris.exception;

/**
 * encountered when there is a problem parsing a date-time string
 */
public class DateTimeException extends IrisException {
    public DateTimeException(String m) {
        super("Date/time cannot be understood:" + m);
    }

    public DateTimeException() {
        super("Date/time cannot be understood.");
    }
}
