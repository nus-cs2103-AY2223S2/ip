package vic.exceptions;

/**
 * A DukeException to check if Date input by user is valid
 */
public class DateParseException extends DukeException {
    /**
     * Constructor for DateParseException
     *
     * @param errorMessage the error date format
     */
    public DateParseException(String errorMessage) {
        super("☹ OOPS!!! Invalid Date format! Please use this "
                + "format: dd/MM/yyyy HHmm! not your own format:" + errorMessage + "!");
    }
}
