package duke.exceptions;

public class MissingNameException extends DukeException {
    /**
     * Initialize a duke.exceptions.MissingNameException exception, which represents
     * the error that the name of the task is missing
     *
     * @return A duke.exceptions.MissingNameException exception
     */
    public MissingNameException() {
        super("OOPS! The task name is missing.");
    }
}
