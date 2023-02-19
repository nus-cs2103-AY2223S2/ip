package duke.exception;

public class DukeCommandNotFoundException extends DukeException {
    /**
     * Constructs a command not found exception.
     */
    public DukeCommandNotFoundException () {
        super("Command not found loll");
    }
}
