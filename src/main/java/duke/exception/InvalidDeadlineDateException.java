package duke.exception;

/**
 * Represents wrong deadline date format
 */
public class InvalidDeadlineDateException extends DukeException {
    public InvalidDeadlineDateException() {
        super("WOOF! The event date time format should be YYYY-MM-DD HHmm! Change it NOW!");
    }
}