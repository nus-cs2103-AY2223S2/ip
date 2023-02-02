package duke;

/**
 * Encapsulates a Task object.
 *
 * @author Sean Chin Jun Kai
 */
public class Task {
    protected String description;

    protected boolean isDone;

    /**
     * Constructor for creating a Task object.
     *
     * @param description Name of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns string representation of the status of the Task.
     *
     * @return Status of Task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks Task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks a Task.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns string representation of a Task object which users can see in the GUI.
     *
     * @return String representation of Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);

    }

    /**
     * Returns string representation of a Task object to store in txt file.
     *
     * @return String representation of Task.
     */
    public String getText() {
        return String.format("| %s | %s", this.getStatusIcon().equals("X") ? "1" : "0", this.description);
    }
}
