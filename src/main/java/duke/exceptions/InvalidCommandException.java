package duke.exceptions;

/**
 * Exception thrown when the command entered by user is invalid.
 *
 * @author Tan Matthew Simon Castaneda
 * @version CS2103 AY22/23 Semester 2
 */
public class InvalidCommandException extends DukeException {

    public InvalidCommandException(String message) {
        super(message);
    }

}
