/**
 * This class represents a task
 */
public class Task {
    private String taskDescription;
    private TaskStatus taskStatus;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.taskStatus = TaskStatus.NOT_DONE;
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
}
