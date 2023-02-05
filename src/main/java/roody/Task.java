package roody;

/**
 * Represents a Task.
 */
public abstract class Task {
    private boolean done;
    private String description;

    /**
     * Creates a Task with specified description.
     * @param description The description of the Task.
     */
    public Task(String description) {  
        this.done = false; 
        this.description = description;
    }
    public boolean isDone() {
        return this.done;
    }
    public void setDone() {
        this.done = true;
    }
    public void setUnDone() {
        this.done = false;
    }
    public char getType() {
        return 'a';
    }

    /**
     * Returns the Task in string format
     * @return Task for saving.
     */
    public String saveTask() {
        return this.description + '|' + this.done;
    }
    @Override
    public String toString() {
        return this.description;
    }
}
