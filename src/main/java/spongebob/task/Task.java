package spongebob.task;

/**
 * Task that given by the user and was recorded in the database.
 */
public class Task {
    /**
     * Description of the task.
     */
    private final String DESCRIPTION;
    /**
     * Status of the task, whether is has been done by the user or not.
     */
    private boolean isDone;

    /**
     * Constructor to create a task.
     *
     * @param description description of the task.
     */
    public Task(String description) {
        this.DESCRIPTION = description;
        this.isDone = false;
    }

    /**
     * Returns 1 if the task has been marked as done, otherwise 0.
     *
     * @return 1 if the task is done, otherwise 0.
     */
    public String getStatusIcon() {
        return (isDone ? "1" : "0"); // mark done task with X
    }

    public String getDescription() {
        return DESCRIPTION;
    }

    public void setDone(boolean b) {
        isDone = b;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), DESCRIPTION);
    }
}
