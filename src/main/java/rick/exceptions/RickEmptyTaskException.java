package rick.exceptions;

/**
 * Represents the exception that arises from an empty task description.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class RickEmptyTaskException extends RickException {
    /**
     * Represents the types of tasks that can cause this Exception.
     */
    public static enum TaskType {
        TYPE_Todo,
        TYPE_Deadline,
        TYPE_Event
    }

    /**
     * Constructs the exception with the given task type.
     *
     * @param type The type of task that was created.
     */
    public RickEmptyTaskException(TaskType type) {
        super(String.format("The description of a %s cannot be empty.",
                type.toString().replace("TYPE_", "")));
    }
}
