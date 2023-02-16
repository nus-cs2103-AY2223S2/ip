package duke.exception;

/**
 * InvalidDateTimeException handles incorrect dateTime inputs
 */
public class InvalidDateTimeException extends DukeException {
    public InvalidDateTimeException() {
        super("DateTime inputs should be in <yyyy-MM-dd>T<HH:mm> format!");
    }
}
