package sebastian.task;

/**
 * Class representing a task
 */
public class Task {
    private String taskDescription;
    private TaskStatus taskStatus;

    /**
     * Constructor
     * @param taskDescription description of the task
     * @param isCompleted whether the task is completed; 0 for not completed, 1 for completed
     */
    public Task(String taskDescription, boolean isCompleted) {
        this.taskDescription = taskDescription;
        this.taskStatus = isCompleted ? TaskStatus.DONE : TaskStatus.NOT_DONE;
    }

    /**
     * Mark a task as done
     * @return the target of invocation
     */
    public Task mark() {
        this.taskStatus = TaskStatus.DONE;
        return this;
    }

    /**
     * Mark a task as not done
     * @return the target of invocation
     */
    public Task unmark() {
        this.taskStatus = TaskStatus.NOT_DONE;
        return this;
    }

    /**
     * Checks if the task description contains the keyword
     * @param keyword the keyword to be checked against
     * @return whether the task description contains the keyword
     */
    public boolean containsKeyword(String keyword) {
        return taskDescription.contains(keyword);
    }

    public void update(String updateDetail) {
        this.taskDescription = updateDetail;
    }

    @Override
    public String toString() {
        return this.taskStatus + " " + this.taskDescription;
    }

    /**
     * Format a task into a suitable String representation to be written to the hard disk
     * @return the formatted String representation
     */
    public String formatForSave() {
        String isCompleted = taskStatus == TaskStatus.DONE ? "DONE" : "NOT_DONE";
        return isCompleted + "<>" + taskDescription;
    }
}
