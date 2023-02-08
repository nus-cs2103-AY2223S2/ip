package duke.task;

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

    /**
     * Returns whether the task is completed.
     *
     * @return true if the task is completed, false otherwise.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Checks if task description contains a keyword.
     *
     * @param keyword Keyword to check.
     * @return true if task description contains the keyword, false otherwise.
     */
    public boolean contains(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Returns the command(s) required to recreate the task.
     *
     * @param id Identifier of the task, usually assigned by task list.
     * @return the command(s).
     */
    public abstract String getRecreateCommand(int id);

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    /**
     * Returns whether this task have the same description as another task.
     *
     * @param other Other object.
     * @return true if other object is a task and has the same description,
     *         false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Task)) {
            return false;
        }
        Task task = (Task) other;
        return description.equals(task.description);
    }
}
