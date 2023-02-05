package duke.exception;

public class InvalidDateTimeException extends DukeException {
    public InvalidDateTimeException() {
        super("Please insert your date using the format, YYYY-MM-DD (e.g. 2000-01-01)");
    }
}
