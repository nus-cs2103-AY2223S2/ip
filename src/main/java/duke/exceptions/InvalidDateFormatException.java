package duke.exceptions;

/** An Exception class that informs user about invalid date time format */
public class InvalidDateFormatException extends DukeException {
    public InvalidDateFormatException() {
        super("OOPS! Invalid date and time format. Please change to YYYY-MM-DD");
    }

}
