package duke.exceptions;

/** An Exception class that informs user about invalid task number */
public class InvalidTaskNumberException extends DukeException {
    /**
     * Initialize an InvalidTaskNumber exception, which represents
     * the error that the task number entered is not valid.
     *
     * @return A InvalidTaskNumber exception
     */
    public InvalidTaskNumberException() {
        super("OOOPS! The task number is not available. Try another number!");
    }
}
