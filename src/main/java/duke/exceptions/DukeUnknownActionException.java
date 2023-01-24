package duke.exceptions;

public class DukeUnknownActionException extends DukeException {

    public DukeUnknownActionException() {
        super("Recognised actions: list, mark, unmark, todo, deadline, event, bye, dueon");
    }
}
