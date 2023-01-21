package sebastianExceptions;

import formatters.Formatter;
import time.DatePattern;

public class DateFormatMismatchException extends InputFormatMismatchException {
    public DateFormatMismatchException() {
        super(
                "Please specify a date in the following format:" + "\n" +
                        Formatter.space() + Formatter.space() + "get " + DatePattern.TASK_ON_DATE_FORMAT
        );
    }
}
