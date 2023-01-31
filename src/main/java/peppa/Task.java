package peppa;

/**
 * Provides a skeletal implementation of a task.
 */
public abstract class Task {
    protected String name;
    protected boolean done;

    public Task() {

    }

    /**
     * Constructs a task with the specified name.
     *
     * @param name Name/description of the task.
     */
    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void setDone(boolean value) {
        this.done = value;
    }

    /**
     * Returns an icon that represents if the task is done.
     *
     * @return "X" if task is completed and " " otherwise.
     */
    public String getStatusIcon() {
        return (done ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + name;
    }

    /**
     * Returns a specially formatted string that is used for writing to storage.
     *
     * @return A specially formatted String representation of the task.
     */
    public abstract String toFormatString();
}
