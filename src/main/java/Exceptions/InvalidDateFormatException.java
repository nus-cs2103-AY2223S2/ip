package exceptions;

import parser.Parser;

/**
 * This class is an exception for Duke receiving an invalid date format
 */
public class InvalidDateFormatException extends DukeException {

    /**
     * Creates an exception of Duke receiving an invalid date format
     *
     * @param s user input string of date
     * @param canBeLocalDate boolean value of whether Local Date format can be accepted 
     */
    public InvalidDateFormatException(String s, boolean... canBeLocalDate) {
        super("Invalid date-time format! Please input " + s
                    + " in the format YYYY-MM-DDThh:mm"
                    + (Parser.parseBoolVarargsToBoolean(canBeLocalDate) 
                        ?  "or YYYY-MM-DD" 
                        : "")
        );
    }
}
