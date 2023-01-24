package duke.query;

import duke.DukeException;

public class InvalidCommandParamException extends DukeException {
    InvalidCommandParamException(String msg) {
        super(msg);
    }
}
