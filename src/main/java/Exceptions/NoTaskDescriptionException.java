package exceptions;

public class NoTaskDescriptionException extends DukeException {
    public NoTaskDescriptionException(String string) {
        super("OOPS!!! The description of a " + string + " task cannot be empty!");
    }
}
