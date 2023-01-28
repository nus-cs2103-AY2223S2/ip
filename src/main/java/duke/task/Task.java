package duke.task;

/**
 * Abstract class contains variables and methods related to Tasks.
 */
public abstract class Task {
    protected boolean completed;
    protected String taskName;

    /**
     * Creates an instance of a Task.
     * @param taskname String name of task.
     */
    public Task(String taskname) {
        this.taskName = taskname;
        this.completed = false;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.completed = true;
    }

    /**
     * Marks task as undone.
     */
    public void markAsUndone() {
        this.completed = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * Marks task as done or undone depending on status.
     * @param status
     */
    public void setCompleted(boolean status) { 
        this.completed = status; 
    }

    /**
     * Returns Task as a String formatted for saving into a file.
     * @return String formatted for fie input.
     */
    public String toFile() {
        if (this.completed) {
            return String.format("1 | %s", this.taskName);
        } else {
            return String.format("0 | %s", this.taskName);
        }
    }

    /**
     * Returns Task as a String.
     * @return Task formatted as a String.
     */
    @Override
    public String toString() {
        String s;
        if (this.completed) {
            s = "[X] " + this.taskName;
        } else {
            s = "[ ] " + this.taskName;
        }
        return s;
    }

}
