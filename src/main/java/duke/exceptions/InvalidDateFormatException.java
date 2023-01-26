package duke.exceptions;

public class InvalidDateFormatException extends DukeException {
    public InvalidDateFormatException() {
        super("OOPS! Invalid date and time format. Please change to YYYY-MM-DD");
    }

}
