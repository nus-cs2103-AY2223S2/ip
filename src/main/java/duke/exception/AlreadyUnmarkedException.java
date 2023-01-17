package duke.exception;

import duke.exception.DukeException;

public class AlreadyUnmarkedException extends DukeException {
    public AlreadyUnmarkedException() {

    }

    public AlreadyUnmarkedException(String message) {
        super(message);
    }

    public AlreadyUnmarkedException(Throwable cause) {
        super(cause);
    }

    public AlreadyUnmarkedException (String message, Throwable cause) {
        super (message, cause);
    }
}
