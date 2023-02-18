package duke.tasks;

/**
 * An abstract task class that encapsulates the description of any type of Task.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructor for a Task object. Only to be used by subclasses.
     * @param description The name of the Task.
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Getter for description of a Task.
     * @return Description as a String.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the done status of a Task.
     * @return True for done tasks, false for undone tasks.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks this Task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * (Un)Marks this task as done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * String representation of any Task, in the format <code>[doneIcon] description</code>.
     * @return String representation of this Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

