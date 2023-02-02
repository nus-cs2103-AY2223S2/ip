package duke.exception.parserexceptions;

import duke.exception.DukeException;

public class UnknownCommandError extends DukeException {
    public UnknownCommandError() {
        super("\n" + "Sorry I don't think there's a command like that!" + "\n");
    }
}
