package exceptions;

import exceptions.DukeException;

public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String description) {
        super("☹ OOPS!!! " + description);
    }
}
