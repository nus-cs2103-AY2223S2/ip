package duke.tasks;

/** Represents a task. */
public abstract class Task {

    private TaskStatus status;
    private int id;

    /**
     * Creates a new task.
     *
     * @param id The id of the task.
     */
    public Task(int id) {
        status = TaskStatus.Pending;
        this.id = id;
    }

    /**
     * Returns true if the task is completed.
     *
     * @return true if the task is completed.
     */
    public boolean isCompleted() {
        return status == TaskStatus.Completed;
    }

    /** Marks the task as completed. */
    public void markCompleted() {
        status = TaskStatus.Completed;
    }

    /** Marks the task as pending. */
    public void markPending() {
        status = TaskStatus.Pending;
    }

    /**
     * Returns the id of the task.
     *
     * @return the id of the task.
     */
    public int id() {
        return this.id;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task.
     */
    public abstract String description();

    /**
     * Returns a string representation of the task.
     *
     * @return a string representation of the task.
     */
    public abstract String serialize();

    /**
     * Deserializes a task from a string.
     *
     * @param s The string to deserialize from.
     * @return The deserialized task.
     */
    public static Task deserialize(String s) {
        switch (s.charAt(0)) {
        case 'T':
            return Todo.deserialize(s);
        case 'D':
            return Deadline.deserialize(s);
        case 'E':
            return Event.deserialize(s);
        default:
            return null;
        }
    }
}
