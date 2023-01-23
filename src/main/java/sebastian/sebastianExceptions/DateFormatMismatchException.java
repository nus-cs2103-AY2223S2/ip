package sebastian.sebastianExceptions;

import sebastian.time.DatePattern;

/**
 * When a date is given in the wrong format
 */
public class DateFormatMismatchException extends InputFormatMismatchException {
    public DateFormatMismatchException() {
        super(
                "Please specify a date in the following format:" + "\n" + "get " + DatePattern.TASK_ON_DATE_FORMAT
        );
    }
}
