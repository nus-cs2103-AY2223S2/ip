package duke;

import duke.DukeException;

public class InvalidDateFormatException extends DukeException {
    public InvalidDateFormatException () {
        super("Please re-enter your request with the date in this format: dd-Mmm-yyyy");
    }
}
