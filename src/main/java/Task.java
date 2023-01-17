public class Task {

    /** Description of the task */
    protected String description;

    /** Whether the task is done */
    protected boolean isDone;

    /**
     * Creates a new task.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Toggles the completion of the task.
     */
    public void toggleDone() {
        this.isDone = !this.isDone;
    }

    @Override
    public String toString() {
        return (this.isDone ? "[X] " : "[ ] ") + this.description;
    }

}
