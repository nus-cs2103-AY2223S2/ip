package clippy.exception;

/**
 * A complaint that the task description is empty.
 *
 * @author chunzkok
 */
public class ClippyTodoEmptyDescriptionException extends ClippyException {
    public ClippyTodoEmptyDescriptionException() {
        super("the description of a todo cannot be empty.");
    }
}
