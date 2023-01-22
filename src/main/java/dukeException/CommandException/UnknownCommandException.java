package dukeException.CommandException;

import dukeException.DukeException;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("HUH? What you say?", null);
    }
}
