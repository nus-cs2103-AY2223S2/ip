package sebastianExceptions;

import formatters.Formatter;
import time.DatePattern;

/**
 * Exception when a deadline is declared using the wrong format
 */
public class DeadlineFormatMismatchException extends InputFormatMismatchException{
    public DeadlineFormatMismatchException() {
        super(
                "Please specify a deadline in the following format:" + "\n" +
                Formatter.space() + Formatter.space() + "deadline [deadline] /by " + DatePattern.USER_INPUT_FORMAT + "\n" +
                        Formatter.space() + "For instance: deadline assignment submission /by 2023-01-27 2359"
        );
    }
}
