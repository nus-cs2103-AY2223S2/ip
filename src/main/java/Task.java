/**
 * Represents a task. Tasks can be in the form of a Todo, a Deadline, or an Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, Boolean bool) {
        this.description = description;
        this.isDone = bool;
    }

    /**
     * Returns the symbol indicating whether a task is done.
     * @return "X" if task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the number indicating whether a task is done.
     * @return 1 if task is done, 0 otherwise.
     */
    public int getStatusNum() { return (isDone ? 1 : 0); }

    /**
     * Marks a task as done.
     * @return Task marked as done.
     */
    public Task markAsDone() {
        return new Task(description, true);
    }

    /**
     * Unmarks a task from being done.
     * @return Task unmarked from being done.
     */
    public Task unmarkAsDone() {
        return new Task(description);
    }

    /**
     * Returns task description.
     * @return Task description.
     */
    public String getDesc() { return description; }

    /**
     * Returns data for storage purposes, should be called in subclasses.
     * @return Data for storage purposes.
     */
    public String getDataToSave() { return "task"; }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
