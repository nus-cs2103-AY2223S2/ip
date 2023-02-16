package exceptions;

public class InvalidDateFormatException extends DukeException {
    public InvalidDateFormatException(String s) {
        super("Invalid date-time format! Please input " + s + " in the format YYYY-MM-DDThh:mm");
    }
}
