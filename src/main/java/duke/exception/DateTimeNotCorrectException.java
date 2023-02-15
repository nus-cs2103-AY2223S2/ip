package duke.exception;

/**
 * Represents empty task list exception
 */
public class DateTimeNotCorrectException extends DukeException {
    public DateTimeNotCorrectException() {
        super("WOOF! What were you thinking boss?? The event date time format should be YYYY-MM-DD HHmm! Change it NOW!");
    }
}
