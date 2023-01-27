package duke.exception;

/**
 * InvalidDateTimeException handles incorrect dateTime inputs
 */
public class InvalidDateTimeException extends DukeException {
    public InvalidDateTimeException() {
        super("Invalid DateTime inputs!");
    }
}
