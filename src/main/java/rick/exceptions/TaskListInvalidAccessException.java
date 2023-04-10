package rick.exceptions;

/**
 * Represents the exception that indicates that an invalid index request was made to
 * a TaskList instance.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class TaskListInvalidAccessException extends RickException {
    /**
     * Constructs the exception instance.
     */
    public TaskListInvalidAccessException() {
        super("An invalid index was entered. Please try again.");
    }
}
