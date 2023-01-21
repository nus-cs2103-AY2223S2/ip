package task;

/**
 * Task containing their relevant information and completion status.
 */
public abstract class Task {

    /** Description of the task */
    protected String description;

    /** Whether the task is done */
    protected boolean isDone;

    /**
     * Constructs a new task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Toggles the completion status of the task.
     */
    public void toggleDone() {
        isDone = !isDone;
    }

    /** Returns whether the task is completed.
     *
     * @return true if the task is completed, false otherwise.
     */
    public boolean getIsDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
    abstract String getRecreateCommand(int id);
}
