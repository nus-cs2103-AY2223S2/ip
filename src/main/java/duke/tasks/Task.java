package duke.tasks;

public class Task {
    private String description;
    private boolean status;

    public Task(String description) {
        this.description = description;
        this.status = false;
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
        this.status = true;
    }

    /**
     * Marks a task as uncompleted.
     */
    public void unmarkDone() {
        this.status = false;
    }

    /**
     * Determines whether a task is completed.
     *
     * @return the completion status of a task
     */
    public boolean isDone() {
        return this.status;
    }

    /**
     * Generates the String representation of the Task for data storage.
     *
     * @return the Task in String format for data storage
     */
    public String getDataString() {
        if (this.isDone()) {
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
        if (this.isDone()) {
            return "[X] " + this.getDescription();
        } else {
            return "[ ] " + this.getDescription();
        }
    }
}