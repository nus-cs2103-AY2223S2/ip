package duke.exception;

/**
 * Represents wrong deadline date format
 */
public class InvalidDeadlineDateException extends DukeException {
    public InvalidDeadlineDateException() {
        super("WOOF! What were you thinking boss?? The deadline date time format should be YYYY-MM-DD! Change it NOW!");
    }
}
