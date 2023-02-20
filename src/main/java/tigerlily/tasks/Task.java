package tigerlily.tasks;

public class Task {
    private String description;
    private boolean isComplete;

    /**
     * Constructor for Task
     * @param description the description of the Task
     */
    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of a task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks a task as completed.
     */
    public void markDone() {
        this.isComplete = true;
    }

    /**
     * Marks a task as uncompleted.
     */
    public void unmarkDone() {
        this.isComplete = false;
    }

    /**
     * Determines whether a task is completed.
     *
     * @return the completion status of a task
     */
    public boolean getStatus() {
        return this.isComplete;
    }

    /**
     * Generates the String representation of the Task for data storage.
     *
     * @return the Task in String format for data storage
     */
    public String getStorageString() {
        if (this.getStatus()) {
            return "1 | " + this.getDescription();
        } else {
            return "0 | " + this.getDescription();
        }
    }

    /**
     * Generates the String representation of the Task.
     *
     * @return the String representation of the Task
     */
    @Override
    public String toString() {
        if (this.getStatus()) {
            return "[X] " + this.getDescription();
        } else {
            return "[ ] " + this.getDescription();
        }
    }
}