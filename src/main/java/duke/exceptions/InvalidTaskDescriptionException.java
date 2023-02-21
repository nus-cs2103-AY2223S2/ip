package duke.exceptions;

/**
 * Exception thrown when the description of task entered by user is not in specified format.
 *
 * @author Tan Matthew Simon Castaneda
 * @version CS2103 AY22/23 Semester 2
 */
public class InvalidTaskDescriptionException extends DukeException {
    public InvalidTaskDescriptionException(String message) {
        super(message);
    }
}
