package tasks;

/**
 * Represents a task that a user can do/plans to do.
 * It has a description and a status (done or not done) of the task.
 */
public abstract class Task {
    protected String description;
    protected TaskType type;
    protected boolean isDone;
    protected Priority priority;

    /**
     * Constructs a new Task instance with the given description and task type.
     *
     * @param description the description of the task.
     * @param type the type of the task.
     */
    public Task(String description, TaskType type, Priority priority) {
        this.description = description;
        this.isDone = false;
        this.type = type;
        this.priority = priority;
    }

    /**
     * Constructs a new Task instance with the given description, status, type.
     *
     * @param description the description of the task.
     * @param isDone the status of the task.
     * @param type the type of the task.
     */
    public Task(String description, boolean isDone, TaskType type, Priority priority) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
        this.priority = priority;
    }

    /**
     * Returns a string representation of the status of the task.
     *
     * @return the string representation of the status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string label of the priority of the task.
     *
     * @return the string label of the priority of the task.
     */
    public String getPriorityLabel() {
        return priority.getValue().toUpperCase();
    }

    /**
     * Returns a string representation of the task.
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] (" + getPriorityLabel() + ") " + this.description;
    }

    /**
     * Returns a string representation of the task suitable for saving in a text file.
     *
     * @return the string representation of the task suitable for saving in a text file.
     */
    public String parseToSave() {
        return (isDone ? 1 : 0) + " | " + description + " | " + getPriorityLabel();
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
