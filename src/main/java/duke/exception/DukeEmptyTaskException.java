package duke.exception;

public class DukeEmptyTaskException extends DukeException {
    /**
     * Constructs an empty task exception.
     */
    public DukeEmptyTaskException () {
        super("The task is empty!!");
    }
}