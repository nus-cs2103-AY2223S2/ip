package duke.exception;

public class InvalidCmdException extends DukeException {
    private String cmd;
    public InvalidCmdException(String msg) {
        super(msg);
    }
}
