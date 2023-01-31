package clippy.exception;

public class ClippyInvalidDateException extends ClippyException {
    public ClippyInvalidDateException() {
        super("Uh-oh, Clippy didn't quite understand the date provided.\n" +
                "clippy.task.Task not saved. Try again with dates in the following format: \n" +
                "===> yyyy-mm-dd <====");
    }
}
