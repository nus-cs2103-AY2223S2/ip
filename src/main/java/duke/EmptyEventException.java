package duke;

import duke.DukeException;

public class EmptyEventException extends DukeException {
    public EmptyEventException() {
        super("OOPS!!! The description of an event cannot be empty.");
    }
}
