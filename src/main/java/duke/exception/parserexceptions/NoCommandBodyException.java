package duke.exception.parserexceptions;

import duke.exception.DukeException;

/**
 * Thrown when the command given does not have a command body or relevant parameters.
 */
public class NoCommandBodyException extends DukeException {
    public NoCommandBodyException() {
        super("\n" + "Oh my, I'm gonna need a command body!" + "\n");
    }
}
