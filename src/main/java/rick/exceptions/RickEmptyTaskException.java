package rick.exceptions;

/**
 * The exception that arises from an empty task description.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class RickEmptyTaskException extends RickException {
    /**
     * The types of tasks that can cause this Exception.
     */
    public static enum TaskType {
        TYPE_Todo,
        TYPE_Deadline,
        TYPE_Event
    }

    /**
     * The default constructor for this exception
     *
     * @param type The type of task that was created.
     */
    public RickEmptyTaskException(TaskType type) {
        super(String.format("The description of a %s cannot be empty.",
                type.toString().replace("TYPE_", "")));
    }
}
