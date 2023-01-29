package rick.exceptions;

/**
 * The exception class that indicates the rick.Rick Store instance is full.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class TaskListFullException extends RickException {
    /**
     * The default constructor for this exception.
     */
    public TaskListFullException() {
        super("Rick is busy and can't take any more tasks");
    }
}
