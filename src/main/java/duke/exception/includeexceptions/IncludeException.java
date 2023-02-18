package duke.exception.includeexceptions;

import duke.exception.DukeException;

/**
 * Parent exception class to be inherited from for exceptions relating to missing keywords in commands.
 */
public class IncludeException extends DukeException {
    public IncludeException(String err) {
        super(err);
    }
}
