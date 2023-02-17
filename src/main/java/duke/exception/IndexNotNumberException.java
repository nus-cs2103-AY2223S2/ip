package duke.exception;

/**
 * Represents Invalid index where index is not number exception
 */
public class IndexNotNumberException extends DukeException {
    public IndexNotNumberException() {
        super("WOOF! Kyle thinks the task index is not a number!");
    }
}

