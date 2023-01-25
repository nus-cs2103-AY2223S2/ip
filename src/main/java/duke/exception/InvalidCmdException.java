package duke.exception;

public class InvalidCmdException extends DukeException{
    public String cmd;
    public InvalidCmdException(String msg) {
        super(msg);
    }
}
