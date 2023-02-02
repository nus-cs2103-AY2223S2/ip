package duke.exception.parserexceptions;

import duke.exception.DukeException;

public class NoCommandBodyException extends DukeException {
    public NoCommandBodyException() {
        super("\n" + "Oh my, I'm gonna need a command body!" + "\n");
    }
}
