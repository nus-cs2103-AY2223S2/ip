package duke.query.exception;

import duke.exception.DukeException;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException(String msg) {
        super(msg);
    }
}
