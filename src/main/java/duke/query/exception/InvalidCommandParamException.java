package duke.query.exception;

import duke.exception.DukeException;

/**
 * InvalidCommandParamException is thrown when a query has an invalid format.
 */
public class InvalidCommandParamException extends DukeException {
    public InvalidCommandParamException(String msg) {
        super(msg);
    }
}
