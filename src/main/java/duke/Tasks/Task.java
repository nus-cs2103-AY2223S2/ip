package duke.tasks;

/**
 * Represents Task class
 */
public class Task {
    private String description;
    private boolean isDone;


    /**
     * Represents Task constructor
     * @param description task name
     * @param isDone task status
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the status icon of the task
     * @return "X" or " " if the task is marked as done, it returns "X". Otherwise, " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : "   ");
    }

    /**
     * Marks status of task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks status of task as undone
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Gets description of the task
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Overrides toString method
     * @return formatted message
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Formats a task into a suitable String
     * @return formatted message
     */
    public String file() {
        String status = this.isDone ? "1" : "0";
        return status + " | " + getDescription();
    }
}
