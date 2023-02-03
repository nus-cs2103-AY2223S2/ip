package duke;

import duke.DukeException;

public class EmptyDeadlineException extends DukeException {
    public EmptyDeadlineException() {
        super("OOPS!!! The description of a deadline cannot be empty.");
    }
}
