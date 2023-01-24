package dude.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    public static int count;

    /**
     * Initializes Task.
     *
     * @param description Description of Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets status icon of Task.
     *
     * @return String of status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks current Task.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks current Task.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns string output of Task.
     *
     * @return String output of Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Adds current task count.
     */
    public static void addTaskCount() {
        count++;
    }

    /**
     * Decreases current task count.
     */
    public static void removeTaskCount() {
        count--;
    }

    /**
     * Returns raw output of Task.
     *
     * @return Raw output of Task.
     */
    public abstract String toRaw();
}
