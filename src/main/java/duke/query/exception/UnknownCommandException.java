package duke.query.exception;

import duke.exception.DukeException;

/**
 * UnknownCommandException is thrown when a command is not recognised.
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException(String msg) {
        super(msg);
    }
}
