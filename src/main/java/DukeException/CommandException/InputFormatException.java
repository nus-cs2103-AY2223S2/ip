package DukeException.CommandException;

import DukeException.DukeException;

public class InputFormatException extends DukeException {
    public InputFormatException(String source, String reason, Throwable err) {
        super("Haiya input format wrong. \nHere is where you mess up: " + source + "\nHere is why you wrong: " + reason, err);
    }
}
