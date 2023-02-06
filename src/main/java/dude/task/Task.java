package dude.task;

/**
 * Task structure for all tasks
 */
public abstract class Task {
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
     * Get current task count.
     */
    public static int getTaskCount() {
        return count;
    }

    /**
     * Check if Task description contains a particular keyword
     *
     * @return Boolean if Task contains keyword
     */
    public boolean contains(String[] keywords) {
        for (String keyword : keywords) {
            if (this.description.toLowerCase().contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns raw output of Task.
     *
     * @return Raw output of Task.
     */
    public abstract String toRaw();
}
