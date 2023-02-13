package dude.task;

/**
 * Task structure for all tasks
 */
public abstract class Task implements Cloneable {
    private static int count;
    protected String description;
    protected boolean isDone;

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
        return (isDone ? "X" : " ");
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
    public static void decreaseTaskCount() {
        count--;
    }

    /**
     * Gets current task count.
     */
    public static int getTaskCount() {
        return count;
    }

    /**
     * Sets current task count.
     *
     * @param count Desired task count to set.
     */
    public static void setTaskCount(int count) {
        Task.count = count;
    }

    /**
     * Checks if Task description contains a particular keyword.
     *
     * @return Boolean if Task contains keyword.
     */
    public boolean contains(String[] keywords) {
        assert keywords != null : "Keywords should not be null";
        for (String keyword : keywords) {
            if (this.description.toLowerCase().contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance.
     * @throws CloneNotSupportedException If cloning has failed.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Returns raw output of Task.
     *
     * @return Raw output of Task.
     */
    public abstract String toRaw();
}
