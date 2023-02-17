package tasks;

/**
 * Represents a task with a description and its status.
 */
public abstract class Task {
    protected boolean isDone;
    protected String description;
    /**
     * Constructor for Task class.
     * @param description what the task entails
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean isCompleted() {
        return this.isDone;
    }

    /**
     * Checks the status of the task and logs to stdout.
     */
    public void mark() {
        markSilent();
        System.out.println("Nice! Marked this task as done, I have:");
    }

    public void markSilent() {
        this.isDone = true;
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
    public abstract String getTaskType();
    /**
     * Data representation of the task to write to file.
     * @return data representation of the task to write to file
     */
    public abstract String writeTask();
}
