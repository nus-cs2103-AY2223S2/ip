package duke.exceptions;

/**
 * Represents an exception that occurs when duration is missing while creating an Event.
 * @author pzhengze
 */
public class DukeMissingEventDateException extends DukeException {
    /**
     * Constructor for DukeMissingEventDateException.
     */
    public DukeMissingEventDateException() {
        super("OOPS!!! The start/end date of an event cannot be empty.");
    }
}
