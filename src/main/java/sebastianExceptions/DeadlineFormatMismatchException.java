package sebastianExceptions;

import formatters.Formatter;

/**
 * Exception when a deadline is declared using the wrong format
 */
public class DeadlineFormatMismatchException extends InputFormatMismatchException{
    public DeadlineFormatMismatchException() {
        super(
                "Please specify a deadline in the following format:" + "\n" +
                Formatter.space() + "deadline [deadline] /by [end_time]"
        );
    }
}
