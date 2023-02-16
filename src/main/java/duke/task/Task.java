package duke.task;

/**
 * Task.
 */
public class Task {
    private String title;
    private boolean isDone;

    /**
     * Constructs a task with default done status.
     *
     * @param title task title
     * @param isDone boolean marked indicator
     */
    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    /**
     * Constructs a task with self define done status.
     *
     * @param title task title
     * @param isDone boolean marked indicator
     */
    public Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    /**
     * Converts mark status in to an string indicator.
     *
     * @return indicator for marked state
     */
    private String getStatusIcon() {
        return (isDone ? "✓" : "   "); // mark done task with X
    }

    /**
     * Mark task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmark completed task.
     */
    public void unmarkDone() {
        isDone = false;
    }

    /**
     * Converts task into csv string.
     *
     * @return in csv string
     */
    public String toCsv() {
        return String.format("%s,%s", (isDone ? 1 : 0), title);
    }

    /**
     * Converts task into a specific string format.
     *
     * @return for output
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), title);
    }
}
