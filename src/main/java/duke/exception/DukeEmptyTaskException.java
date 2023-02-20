package duke.exception;

/**
 * Represents an empty task exception local to Duke bot.
 */
public class DukeEmptyTaskException extends DukeException {
    /**
     * Constructs an empty task exception.
     */
    public DukeEmptyTaskException() {
        super("The task is empty!!");
    }
}
