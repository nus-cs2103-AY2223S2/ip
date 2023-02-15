package duke.query.exception;

import duke.exception.DukeException;

public class InvalidCommandParamException extends DukeException {
    public InvalidCommandParamException(String msg) {
        super(msg);
    }
}
