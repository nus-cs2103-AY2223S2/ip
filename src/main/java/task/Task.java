package task;

/**
 * This class represents a task
 */
public class Task {
    private final String taskDescription;
    private TaskStatus taskStatus;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.taskStatus = TaskStatus.NOT_DONE;
    }

    public Task(String taskDescription, int isCompleted) {
        this.taskDescription = taskDescription;
        this.taskStatus = isCompleted == 0? TaskStatus.NOT_DONE : TaskStatus.DONE;
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

    @Override
    public String toString() {
        return this.taskStatus + " " + this.taskDescription;
    }

    public String formatForSave() {
        int isCompleted = taskStatus == TaskStatus.DONE? 1 : 0;
        return isCompleted + "<>" + taskDescription;
    }
}
