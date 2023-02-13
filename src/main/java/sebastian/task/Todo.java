package sebastian.task;

/**
 * Class represring a todo
 */
public class Todo extends Task {

    private static final String taskType = "T";

    /**
     * Constructor
     * @param isCompleted whether the to-do is completed
     * @param taskDescription description of the to-do
     */
    public Todo(boolean isCompleted, String taskDescription) {
        super(taskDescription, isCompleted);
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString();
    }

    /**
     * Format the to-do into a suitable String representation to be written to the hard disk
     * @return the formatted String representation
     */
    @Override
    public String formatForSave() {
        return taskType + "<>" + super.formatForSave();
    }
}

