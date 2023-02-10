package duke.exception;

/**
 * Represents wrong deadline date format
 */
public class InvalidDeadlineDateException extends DukeException {
    public InvalidDeadlineDateException() {
        super("WOOF! The deadline date time format should be YYYY-MM-DD! Change it NOW!");
    }
}
