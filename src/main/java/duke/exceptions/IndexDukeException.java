package duke.exceptions;

/**
 * A class that represents errors created from trying to access an invalid number on a
 * ToDoList object while Duke is running.
 */
public class IndexDukeException extends DukeException {
    public IndexDukeException() {
        super("It seems like the number given isn't on the list!");
    }
}
