package tasks;

public class Task {
    protected String description;
    protected boolean isDone;
    
    /**
     * Constructor for Task.
     * 
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * 
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    
    /**
     * Returns the description of the task.
     * 
     * @return The description of the task.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the description of the task.
     * 
     * @return The description of the task.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     * 
     * @return The description of the task.
     */
    public String writeToFile() {
        return description;
    }
}
