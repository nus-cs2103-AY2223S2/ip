package duke.exceptions;

public class DateTimeFormatException extends DukeException {
    public DateTimeFormatException() {
        super("Please input the datetime format in /by DD/MM/YY HHMM.");
    }
}

