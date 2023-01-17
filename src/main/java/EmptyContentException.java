public class EmptyContentException extends DukeException {
    public EmptyContentException() {
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}
