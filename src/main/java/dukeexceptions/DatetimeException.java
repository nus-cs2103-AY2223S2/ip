package dukeexceptions;

/**
 * Encapsulates an Exception when datetime format is invalid
 */
public class DatetimeException extends DukeException {

    /**
     * Constructor.
     *
     */
    public DatetimeException() {
        super("Incorrect Datetime format. Datetime format should be in 'yyyy/MM/d HHmm'.");
    }
}
