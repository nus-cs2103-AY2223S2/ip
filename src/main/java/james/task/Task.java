package james.task;

/**
 * Represents a Task in the task list.
 */
public class Task {
    /** The description of the task. */
    protected String description;

    /**
     * boolean field for marking and unmark of tasks
     */
    protected boolean isDone;


    /**
     * Constructs Task object.
     *
     * @param description The task the user types in.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon.
     *
     * @return "X" if the task is complete;
     *         " " if the task is incomplete.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getIsDone() {
        return this.isDone;
    }


    /**
     * Returns the task represented by a string displayed to the user.
     *
     * @return The string representation of a Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the Task represented by a string to be stored in the taskList
     * file in storage file.
     *
     * @return The string representation of stored Task.
     */
    public String toStoreString() {
        String isDoneIndicator = isDone ? "1" : "0";
        return isDoneIndicator + " | " + this.description;
    }


}



