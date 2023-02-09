package duke.task;

/**
 * Contains information of a task.
 * Contains description and completion status of the task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Generates new task from description.
     *
     * @param description Description of new task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Generates new task from description.
     * Allows completion status to be specified.
     *
     * @param description Description of new task.
     * @param isDone Completion status of new task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Changes completion status to done.
     */
    public void mark() {
        assert !isDone : "Task was already done";
        this.isDone = true;
    }

    /**
     * Changes completion status to not done.
     */
    public void unmark() {
        assert isDone : "Task was not done";
        this.isDone = false;
    }

    /**
     * Returns the task's saved data in string format.
     *
     * @return The task's saved data in string format.
     */
    public String save() {
        return "  | " + getStatusIcon()
                + " | " + description;
    }

    /**
     * Checks if specified task is a duplicate.
     *
     * @param task Task to compare to.
     * @return True or False.
     */
    public boolean isDup(Task task) {
        boolean isSameClass = getClass()
                .equals(task.getClass());
        boolean isSameDescription = description
                .equals(task.getDescription());
        boolean isSameStatus = getStatusIcon()
                .equals(task.getStatusIcon());

        return isSameClass
                && isSameDescription
                && isSameStatus;
    }

    /**
     * Returns the task's details.
     *
     * @return Task's details.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] "
                + description;
    }
}
