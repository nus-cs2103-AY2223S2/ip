/**
 * The base class for all tasks
 */
public class Task {
    protected String name;
    protected boolean isDone;

    /**
     * The default constructor
     */
    public Task() {
        this("");
    }

    /**
     * The task string description
     * @param description: a string describing the task
     */
    public Task(String description) {
        this.name = description;
        this.isDone = false;
    }

    /**
     * Get the name of the task
     * @return the string name of the task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get whether the task is done
     * @return the status of the task
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Mark this task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark this task as undone
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Get the string icon of the status of the task
     * @return the string icon of the task status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Overriding
     * @return the task string
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), name);
    }
}
