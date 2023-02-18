package duke.exception.blankfieldexceptions;

import duke.exception.DukeException;

/**
 * Thrown when a AddEvent command does not include the "/from" or the "/to" keyword.
 */
public class BlankFieldException extends DukeException {
    public BlankFieldException(String err) {
        super(err);
    }
}
