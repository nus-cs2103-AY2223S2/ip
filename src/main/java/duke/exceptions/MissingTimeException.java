package duke.exceptions;

public class MissingTimeException extends DukeException {
    /**
     * Initialize a duke.exceptions.MissingTimeException exception, which represents
     * the error that the time field is missing
     *
     * @return A duke.exceptions.MissingTimeException exception
     */
    public MissingTimeException() {
        super("OOPS! The time element is missing.");
    }
}
