package roody.tasks;

/**
 * Represents a Task.
 */
public abstract class Task {
    private boolean isDone;
    private String description;

    /**
     * Creates a Task with specified description.
     * @param description The description of the Task.
     */
    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }
    public boolean isDone() {
        return this.isDone;
    }
    public void setDone() {
        this.isDone = true;
    }
    public void setUnDone() {
        this.isDone = false;
    }
    public char getType() {
        return 'a';
    }

    /**
     * Returns the Task in string format
     * @return Task for saving.
     */
    public String saveTask() {
        return this.description + '|' + this.isDone;
    }
    @Override
    public String toString() {
        return this.description;
    }
}
