package duke.exceptions;

public class StorerEmptyException extends DukeException {
    public StorerEmptyException() {
        super("☹ OOPS!!! I'm sorry, your list is empty :-(");
    }
}
