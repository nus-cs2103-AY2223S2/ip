package angela.task;

/**
 * Represents a To-Do Task.
 */
public class ToDoTask extends Task {

    private static final String EVENT_SYMBOL = "T";

    /**
     * Creates a To-Do Task.
     * @param description The description of the task.
     */
    public ToDoTask(String description) {
        super(description, EVENT_SYMBOL);
    }

    /**
     * Returns false. To-Do task is never upcoming.
     * @param maxDays Number of days to the task from now in which this method should return true.
     * @return False.
     */
    @Override
    public boolean isUpcoming(long maxDays) {
        return false;
    }
}
