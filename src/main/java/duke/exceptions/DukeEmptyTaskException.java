package duke.exceptions;

/**
 * The exception that arises from an empty task description.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class DukeEmptyTaskException extends DukeException {
    public static enum TaskType {
        Todo,
        Deadline,
        Event,
        Unknown
    }

    /**
     * The default constructor for this exception
     * @param type The type of task that was created.
     */
    public DukeEmptyTaskException(TaskType type) {
        super(String.format("The description of a %s cannot be empty.", type));
    }
}
