package duke.exception;

/**
 * A DukeException that deals with end date-times being after start date-times.
 */
public class InvalidTaskDurationException extends DukeException {
    /**
     * Constructor for InvalidTaskDurationException.
     */
    public InvalidTaskDurationException() {
        super("Your task must begin before it starts!");
    }
}
