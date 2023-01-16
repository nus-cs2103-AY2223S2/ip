package duke.task;

/**
 * Task
 */
public class Task {
    private String title;
    private boolean isDone;

    /**
     * Defualt constructor with default done status.
     * 
     * @param title
     * @param isDone
     */
    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    /**
     * Defualt constructor with self define done status.
     * 
     * @param title
     * @param isDone
     */
    public Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    /**
     * Converts mark status in to an string indicator.
     * 
     * @return {@link String} object
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
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
     * @return {@link String} object
     */
    public String toCsv() {
        return String.format("%s,%s", (isDone ? 1 : 0), title);
    }

    /**
     * Converts task into a specific string format.
     * 
     * @return {@link String} object
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), title);
    }
}
