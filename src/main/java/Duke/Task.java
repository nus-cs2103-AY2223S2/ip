package Duke;

/**
 * Abstract class representing a task.
 * @author Bryan Juniano
 */

public abstract class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructor for task.
     * @param name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Getter for task isDone status.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks task.
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Generates the string representation of the task.
     */
    @Override
    public String toString() {
        if(this.isDone) {
            return "[X] | " + this.name;
        }
        else {
            return "[ ] | " + this.name;
        }
    }
}