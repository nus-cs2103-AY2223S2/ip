package sebastian.exceptions;

import sebastian.time.DatePattern;

/**
 * Exception when a deadline is declared using the wrong format
 */
public class DeadlineFormatMismatchException extends InputFormatMismatchException {
    /**
     * Constructor
     */
    public DeadlineFormatMismatchException() {
        super(
                "Please specify a deadline in the following format:" + "\n" + "deadline [deadline] /by "
                        + DatePattern.USER_INPUT_FORMAT + "\n"
                        + "For instance: deadline assignment submission /by 2023-01-27 2359"
        );
    }
}
