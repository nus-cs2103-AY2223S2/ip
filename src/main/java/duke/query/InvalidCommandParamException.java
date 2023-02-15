package duke.query;

import duke.DukeException;

public class InvalidCommandParamException extends DukeException {
    public InvalidCommandParamException(String msg) {
        super(msg);
    }
}
