package duke.exceptions;

public class DukeEmptyListException extends DukeException {

    public DukeEmptyListException() {
        super("The list is empty.");
    }
}
