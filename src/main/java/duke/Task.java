package duke;

/**
 * Represents a task (that can be of type "event", "todos" and "deadline")
 * <p></p>
 * <b>Note:</b> Abstract class that needs to be implemented
 *
 * @author MrTwit99
 * @since 2023-02-01
 */
public abstract class Task {
    protected String taskInfo;
    protected boolean hasCompleted;

    /**
     * Returns a Task object that stores information about the task description.
     *
     * @param taskInfo Task description.
     */
    public Task(String taskInfo) {
        this.taskInfo = taskInfo;
        this.hasCompleted = false;
    }

    /**
     * Returns a boolean value indicating the completion status of the Task.
     *
     * @return Boolean value indicating the completion status of the Task.
     */
    public boolean getStatus() {
        return !this.hasCompleted;
    }

    /**
     * Returns a string that indicates the Task has been completed.
     *
     * @return String message that indicates the completion of a Task.
     */
    public String setDone() {
        this.hasCompleted = true;
        return "     Nice! I've marked this task as done:\n       " + this.getTaskInfoStatus();
    }

    /**
     * Returns a string that indicates the Task yet to complete.
     *
     * @return String message that indicates the Task is yet to complete.
     */
    public String setIncomplete() {
        this.hasCompleted = false;
        return "     Alright, I've marked this task as not done yet:\n       " + this.getTaskInfoStatus();
    }

    /**
     * Returns a string on the information about the Task that is to be added to the ongoing taskList.
     *
     * @return String message of the Task description and status.
     */
    public String getTaskInfoStatus() {
        if (hasCompleted) {
            return "[X] " + this.taskInfo;
        } else {
            return "[ ] " + this.taskInfo;
        }
    }

    /**
     * Returns a string on the information about the Task that is to be saved to the file allocated by Storage.
     * <p></p>
     * <b>Note:</b> Abstract method that needs to be implemented.
     *
     * @return String message of the Task description and status..
     */
    public abstract String getTaskInfo();

    /**
     * Return boolean value indicating whether the current Task is ongoing.
     *
     * @return Boolean value indicating whether the current Task is ongoing.
     */
    public abstract boolean isOngoing();
}
