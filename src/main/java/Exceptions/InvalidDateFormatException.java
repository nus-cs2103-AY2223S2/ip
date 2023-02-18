package exceptions;

/**
 * This class is an exception for Duke receiving an invalid date format
 */
public class InvalidDateFormatException extends DukeException {

    /**
     * Creates an exception of Duke receiving an invalid date format
     *
     * @param s user input string of date
     */
    public InvalidDateFormatException(String s) {
        super("Invalid date-time format! Please input " + s + " in the format YYYY-MM-DDThh:mm");
    }
}
