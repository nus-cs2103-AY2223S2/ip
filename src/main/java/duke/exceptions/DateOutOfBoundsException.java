package duke.exceptions;

/**
 * Exception thrown when the dates month and days are out of bounds.
 *
 * @author Tan Matthew Simon Castaneda
 * @version CS2103 AY22/23 Semester 2
 */
public class DateOutOfBoundsException extends DukeException {
    public DateOutOfBoundsException(String message) {
        super(message);
    }
}
