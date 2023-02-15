package duke.exception;

/**
 * Represents invalid event date-time format error
 */
public class InvalidEventDateTimeException extends DukeException {
    public InvalidEventDateTimeException() {
        super("WOOF WHAT?! The event date time format should be YYYY-MM-DD HHmm! Change it NOW, Boss!");
    }
}
