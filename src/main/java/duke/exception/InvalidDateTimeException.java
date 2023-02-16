package duke.exception;

import duke.Parser;

/**
 * A DukeException that deals with incorrect date time format that the user inputs.
 */
public class InvalidDateTimeException extends DukeException{
    /**
     * Constructor for InvalidDateTimeException.
     */
    public InvalidDateTimeException() {
        super(String.format("Invalid date time! Please use the following format:\n\t\"%s\"",
                Parser.DATE_TIME_READ_FORMAT.toUpperCase()));
    }
}
