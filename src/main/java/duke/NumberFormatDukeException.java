package duke;

/**
 * Exception for number format
 */
public class NumberFormatDukeException extends DukeException {
    public NumberFormatDukeException() {
        super("Please specify a number instead. ");
    }
}
