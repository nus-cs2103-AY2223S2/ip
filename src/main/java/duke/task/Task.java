package duke.task;

/**
 * Represents a task to be put into the task list.
 * All other tasks inherit from this class.
 */
public abstract class Task {
    protected String taskName;
    protected boolean isDone;

    /**
     * Represents a constructor to make a task object with a given task name.
     *
     * @param taskName The name of the Task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * If the task is done, "X" is returned, otherwise " " is returned.
     *
     * @return String representing the status icon of the task "X" or " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     *
     * @return String to be shown to the user.
     */
    public String mark() {
        if (!isDone) {
            this.isDone = true;
            String toShow = "Nice! I've marked this task as done!\n"
                    + String.format("[%s] %s", this.getStatusIcon(), this.taskName);
            return toShow;
        } else {
            String toShow = "The task is already done!";
            return toShow;
        }
    }

    /**
     * Returns the task name as a string
     *
     * @return String task name
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns the string of the marked task.
     *
     * @return String to be shown to the user.
     */
    public String unmark() {
        if (isDone) {
            this.isDone = false;
            String toShow = "I've marked this task as undone, you lazy bum\n"
                    + String.format("[%s] %s", this.getStatusIcon(), this.taskName);
            return toShow;
        } else {
            String toShow = "The task is already done!";
            return toShow;
        }
    }

    /**
     * Returns the String array of the encoded the Task object into
     * a String array for saving into the save file.
     *
     * @return String array of the encoded Deadline object.
     */
    public abstract String[] encode();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.taskName);
    }
}
