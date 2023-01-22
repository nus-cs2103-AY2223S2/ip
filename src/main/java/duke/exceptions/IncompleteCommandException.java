package duke.exceptions;

public class IncompleteCommandException extends DukeException {
    public IncompleteCommandException(String content) {
        super("☹ OOPS!!! The " + content + " cannot be empty from your command");
    }
}
