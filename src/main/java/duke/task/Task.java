package duke.task;

/**
 * Represents a Task, from which all other tasks inherit from
 */
public class Task {
    protected static final boolean IS_DONE = false;

    private final String task;
    private boolean isDone;

    /**
     * Returns a Task with task stored that is not done
     *
     * @param task String of task to be stored
     */
    public Task(String task) {
        this(task, IS_DONE);
    }

    /**
     * Returns a Task with task and isDone stored
     *
     * @param task String of task to be stored
     * @param isDone boolean of if the task is done
     */
    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
        assert task.length() > 0 : "Task description should not be empty!";
    }

    /**
     * Returns true if the task is done and false otherwise
     *
     * @return isDone boolean
     */
    public boolean isDone() {
        return this.isDone;
    }
    /**
     * Sets task as done
     */
    public void setDone() {
        this.isDone = true;
    }
    /**
     * Sets task as not done
     */
    public void setNotDone() {
        this.isDone = false;
    }
    /**
     * Returns a boolean: true if the task is a Deadline, false otherwise
     *
     * @return boolean if the command is a Deadline
     */
    public boolean isDeadline() { return false; }
    public void snooze() {

    }
    /**
     * Returns the String task without any formatting
     *
     * @return task String
     */
    protected String getTask() {
        return this.task;
    }
    /**
     * Returns the String of the task formatted to be displayed
     *
     * @return String formatted String
     */
    @Override
    public String toString() {
        String markedAsDone = isDone ? "X" : " ";
        return String.format("[%s] %s", markedAsDone, task);
    }

    /**
     * Returns the String of the task used to be saved
     *
     * @return String formatted String
     */
    public String saveString() {
        int done = isDone() ? 1 : 0;
        return String.format("Task | %d | %s", done, task);
    }
}
