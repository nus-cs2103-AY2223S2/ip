public class DukeUnknownActionException extends DukeException {

    DukeUnknownActionException() {
        super("Recognised actions: list, mark, unmark, todo, deadline, event, bye");
    }
}
