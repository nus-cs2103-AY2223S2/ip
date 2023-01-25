package duke.exceptions;

/**
 * The exception class that indicates the Duke Store instance is full.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class TaskListFullException extends DukeException{
    /**
     * The default constructor for this exception.
     */
    public TaskListFullException() {
        super("Rick is busy and can't take any more tasks");
    }
}
