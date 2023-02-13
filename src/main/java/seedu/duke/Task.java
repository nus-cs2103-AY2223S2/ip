package seedu.duke;

/**
 * Represents Task object.
 */
public class Task {
    /** Description of the Task */
    protected String description;
    /** Status of the Task */
    protected boolean isDone;

    /**
     * Creates a Task object.
     *
     * @param description the details of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Checks if the task is done.
     *
     * @return "[X]" if task is done, and "[ ]" if task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets completion status of the task.
     *
     * @param isDone mark task as True if done, and False if not done.
     */
    public void toMark(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Represents the task to be displayed on the user interface.
     *
     * @return String format of a Task object to be displayed with the status icon.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }

    /**
     * Represents the saved file format of a Task object.
     *
     * @return String format of a Task object in saved file form, 1 if task is done, 0 if task is not done.
     */
    public String toSave() {
        if (isDone) {
            return "1 | " + this.description;
        } else {
            return "0 | " + this.description;
        }
    }
}

