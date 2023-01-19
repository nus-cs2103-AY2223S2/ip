public class MissingNameException extends DukeException {
    /**
     * Initialize a MissingNameException exception, which represents
     * the error that the name of the task is missing
     *
     * @return A MissingNameException exception
     */
    public MissingNameException() {
        super("OOPS! The task name is missing.");
    }
}
