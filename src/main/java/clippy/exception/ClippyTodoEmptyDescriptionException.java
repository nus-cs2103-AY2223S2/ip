package clippy.exception;

/**
 * A complaint that the task description is empty.
 *
 * @author chunzkok
 */
public class ClippyTodoEmptyDescriptionException extends ClippyException {
    /**
     * Creates a ClippyTodoEmptyDescriptionException that contains a message
     * on the necessity of a descrption for a Todo task.
     */
    public ClippyTodoEmptyDescriptionException() {
        super("the description of a todo cannot be empty.");
    }
}
