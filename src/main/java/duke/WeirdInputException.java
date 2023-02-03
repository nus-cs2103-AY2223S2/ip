package duke;

import duke.DukeException;

public class WeirdInputException extends DukeException {
    public WeirdInputException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
