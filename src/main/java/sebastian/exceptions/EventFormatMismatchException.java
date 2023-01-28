package sebastian.exceptions;

import sebastian.time.DatePattern;

/**
 * Exception when an event is declared using the wrong format
 */
public class EventFormatMismatchException extends InputFormatMismatchException {
    /**
     * Constructor
     */
    public EventFormatMismatchException() {
        super(
                "Please specify a deadline in the following format: " + "\n"
                       + "event [event] /from " + DatePattern.USER_INPUT_FORMAT + " /to "
                        + DatePattern.USER_INPUT_FORMAT + "\n"
                       + "For instance: event project meeting /from 2023-01-30 1600 /to 2023-01-30 1800"
        );
    }
}
