package duke.exception;

public class DukeCommandNotFoundException extends DukeException {
    public DukeCommandNotFoundException () {
        super("Command not found!!");
    }
}
