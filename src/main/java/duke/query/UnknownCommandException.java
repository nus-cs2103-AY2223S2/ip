package duke.query;

import duke.DukeException;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException(String msg) {
        super(msg);
    }
}
