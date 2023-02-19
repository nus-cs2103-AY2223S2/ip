package treebot.tasks;

public abstract class Task {
    protected boolean isDone = false;
    protected String taskDescription;

    /**
     * Returns a Task with the given task description.
     *
     * @param taskDescription
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     *  Formats the object to be persisted in storage.
     *
     * @return A formatted string for persistence.
     */
    abstract public String toStorageFormatString();


    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + taskDescription;
        }
        return "[] " + this.taskDescription;
    }


}
