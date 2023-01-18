public class ClippyTodoEmptyDescriptionException extends ClippyException {
    public ClippyTodoEmptyDescriptionException() {
        super("the description of a todo cannot be empty.");
    }
}
