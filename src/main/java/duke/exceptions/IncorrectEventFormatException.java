package duke.exceptions;

/**
 * Exception is thrown when Event is created in
 * wrong format (not {description} /from {start date} /to {end date})
 */
public class IncorrectEventFormatException extends NeroException {

    public IncorrectEventFormatException() {
        super("Format of an event should be: {description} /from {start date} /to {end date}");
    }

}
