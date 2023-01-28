package panav.task;

/**
 * Abstract class to represent a Task in the TaskList.
 */
public abstract class Task {
    protected String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method to show if a task is completed or not.
     * @return "X" is task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Method to mark a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Method to mark a task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
