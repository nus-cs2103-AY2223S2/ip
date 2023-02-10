package duke.exception;

/**
 * Represents wrong event date-time format
 */
public class InvalidEventDateTimeException extends DukeException {
    public InvalidEventDateTimeException() {
        super("WOOF! The event date time format should be YYYY-MM-DD HHmm! Change it NOW!");
    }
}
