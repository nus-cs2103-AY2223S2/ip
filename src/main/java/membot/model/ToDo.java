package membot.model;

/**
 * Represents a basic <code>Task</code> object.
 */
public class ToDo extends Task {
    public static final String TAG = "[T]";

    public ToDo(String title) {
        super(title);
    }

    /**
     * Returns the <code>Task</code> type.
     *
     * @return The <code>Task</code> type.
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.TODO;
    }

    /**
     * Returns <code>"~"</code> to signify that there is no deadline attached
     * to any <code>ToDo</code> task.
     *
     * @return <code>"~"</code>.
     */
    @Override
    public String getDeadline() {
        return Task.EMPTY;
    }

    /**
     * Returns <code>"~"</code> to signify that there is no start dateTime attached
     * to any <code>ToDo</code> task.
     *
     * @return <code>"~"</code>.
     */
    @Override
    public String getStartDateTime() {
        return Task.EMPTY;
    }

    /**
     * Returns <code>"~"</code> to signify that there is no end dateTime attached
     * to any <code>ToDo</code> task.
     *
     * @return <code>"~"</code>.
     */
    @Override
    public String getEndDateTime() {
        return Task.EMPTY;
    }

    /**
     * Returns a <code>String</code> representation of the <code>ToDo</code> task.
     *
     * @return A <code>String</code> representation of the <code>ToDo</code> task.
     */
    @Override
    public String toString() {
        return ToDo.TAG + super.toString();
    }
}
