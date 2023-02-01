package duke.Tasks;

/**
 * The class representing a ToDo task.
 */
public class ToDo extends Task {
    private static final String FORMAT = "todo {task name}";
    public ToDo(String desc) {
        super(desc);
    }
    
    /**
     * User friendly guide to help users if InvalidCommandException is thrown.
     * 
     * @return String format of a valid ToDo command.
     */
    public static String showFormat() {
        return "Create a `todo` with: " + FORMAT;
    }

    /**
     * User friendly interpretation of ToDo task object.
     * Displays Task type, isDone status and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
