package duke.exceptions;

/**
 * Represents an exception that occurs when due date is missing while creating a Deadline.
 * @author pzhengze
 */
public class DukeMissingDeadlineException extends DukeException {
    /**
     * Constructor for DukeMissingDeadlineException.
     */
    public DukeMissingDeadlineException() {
        super("OOPS!!! The date/time of a deadline cannot be empty.");
    }
}
