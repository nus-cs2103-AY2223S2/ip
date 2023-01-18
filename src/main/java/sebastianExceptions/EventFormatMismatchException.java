package sebastianExceptions;

import formatters.Formatter;

/**
 * Exception when an event is declared using the wrong format
 */
public class EventFormatMismatchException extends InputFormatMismatchException{
    public EventFormatMismatchException() {
        super(
                "Please specify a deadline in the following format: " + "\n" +
                        Formatter.space() + "event [event] /from [start_time] /to [end_time]"
        );
    }
}
