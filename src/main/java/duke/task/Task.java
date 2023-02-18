package duke.task;

/**
 * Creates a new task.
 *
 * @author Evan Lee
 * @version CS2103 AY22/23 Semester 2
 */
public class Task {
    private String task;
    private boolean isMarked = false;

    /**
     * A public constructor to initialize Task instance.
     *
     * @param task Task name.
     */
    Task(String task) {
        this.task = task;
    }

    /** Marks the task as completed. */
    public void mark() {
        this.isMarked = true;
    }

    /** Marks the task as uncompleted. */
    public void unmark() {
        this.isMarked = false;
    }

    /**
     * Checks mark status and returns appropriate icon.
     *
     * @return Icon.
     */
    public String getStatusIcon() {
        return (isMarked ? "[X]" : "[ ]");
    }

    /**
     * Returns the name of the task.
     *
     * @return Task name.
     */
    public String getTaskName() {
        return task;
    }

    /**
     * Returns the description of Task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return getTaskName();
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.task.toString();
    }
}
