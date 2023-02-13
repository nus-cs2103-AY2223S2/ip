package sebastian.exceptions;

import sebastian.time.DatePattern;

/**
 * Exception when a date is given in the wrong format
 */
public class GetFormatMismatchException extends InputFormatMismatchException {
    /**
     * Constructor
     */
    public GetFormatMismatchException() {
        super(
                "Please specify a date in the following format:" + "\n" + "get " + DatePattern.TASK_ON_DATE_FORMAT
        );
    }
}
