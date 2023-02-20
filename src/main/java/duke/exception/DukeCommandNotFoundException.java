package duke.exception;

/**
 * Represents a command not found exception local to Duke task manager.
 */
public class DukeCommandNotFoundException extends DukeException {
    /**
     * Constructs a command not found exception.
     */
    public DukeCommandNotFoundException() {
        super("Command not found!!");
    }
}
