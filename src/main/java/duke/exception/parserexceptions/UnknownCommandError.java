package duke.exception.parserexceptions;

import duke.exception.DukeException;

/**
 * Thrown when the command is unknown or when parsable in Duke.
 */
public class UnknownCommandError extends DukeException {
    public UnknownCommandError() {
        super("\n" + "Sorry I don't think there's a command like that!" + "\n");
    }
}
