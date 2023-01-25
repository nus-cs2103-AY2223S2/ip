package duke.exceptions;

/**
 * The exception class that indicates that an invalid index request was made to
 * a DukeStore instance.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class TaskListInvalidAccessException extends DukeException {
    /**
     * The default constructor for this exception.
     */
    public TaskListInvalidAccessException() {
        super("An invalid index was entered. Please try again.");
    }
}
