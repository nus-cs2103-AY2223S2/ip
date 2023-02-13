package duke.tasks;

/**
 * The class representing a ToDo task
 */
public class ToDo extends Task {
    private static final String FORMAT = "todo {task name}";
    private final boolean hasDate = false;

    /**
     * The constructor to initialise a ToDo task object with the given description.
     * @param desc Description of the task.
     */
    public ToDo(String desc) {
        super(desc);
    }

    /**
     * User friendly guide to help users in case of InvalidCommandException
     * @return String format of a valid ToDo command.
     */
    public static String showFormat() {
        return "Create a `todo` with: " + FORMAT;
    }

    /**
     * User friendly interpretation of ToDo task object.
     * Displays Task type, isDone status and description.
     * @return String representation of ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
