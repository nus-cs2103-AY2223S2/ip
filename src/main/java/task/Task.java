package task;

/**
 * Represents a Task.
 * Expected to turn abstract.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object.
     * Meant for subclasses.
     *
     * @param description String to accompany the task describing the task.
     * @returns Task object.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the String representation of the status icon of this task.
     *
     * @return String representation of the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks Task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks Task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns a boolean representing if Task is marked as done
     *
     * @return boolean representing if Task is marked as done
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns String representation of Task object.
     *
     * @return String representation of Task object.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    /**
     * Returns a boolean for whether another object and this object is equal.
     * Overridden equals method for object.
     * Check if an object and this object can be treated as equal.
     *
     * @param o object to be compared to.
     * @return boolean representing if the object being compared to is equal to this object.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Task)) {
            return false;
        }

        return ((Task) o).description == this.description && ((Task) o).isDone == this.isDone;
    }

    /**
     * Returns String representation of command that can be used to create a similar task event.
     * Unused.
     *
     * @return String representation of command that can be used to create a similar task event.
     */
    public String getCommand() {
        return this.description;
    }
}
