package duke.exceptions;

/**
 * Exception is thrown when dates are in wrong format (not yyyy-mm-dd)
 */

public class IncorrectDateFormatException extends NeroException {

    public IncorrectDateFormatException() {
        super("Dates should be in yyyy-mm-dd format!!!");
    }
}
