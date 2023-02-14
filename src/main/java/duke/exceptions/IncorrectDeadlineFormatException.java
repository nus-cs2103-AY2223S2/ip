package duke.exceptions;

/**
 * Exception is thrown when Deadline is created in wrong format (not {description} /by {date})
 */
public class IncorrectDeadlineFormatException extends NeroException {

    public IncorrectDeadlineFormatException() {

        super("Format of a Deadline should be : {description} /by {date} ");
    }

}
