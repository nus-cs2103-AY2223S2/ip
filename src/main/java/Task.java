abstract public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Checks the status of the task and logs to stdout.
     */
    public void mark() {
        this.isDone = true;
        System.out.println("Nice! Marked this task as done, I have:");
    }

    /**
     * Unchecks the status of the task and logs to stdout.
     */
    public void unmark() {
        this.isDone = false;
        System.out.println("Ok! Marked this task as not done yet, I have:");
    }

    /**
     * Getter of task description.
     * @return String representation of the task's description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Abstract method for child classes to return their type.
     * @return string type of task
     */
    abstract public String getTaskType();
}
