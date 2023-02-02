package duke.tasklist;

/**
 * Represents an instance of a Task that the user inputs.
 * A Task is created by the user.
 */
public class Task {
    protected String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the completion status of the Task
     *
     * @return String representation of completion
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Changes the boolean field marking the completion status of the task
     */
    public void changeCompletion() {
        this.isDone = !this.isDone;
    }

    /**
     * Returns a String representation of a Task.
     * Appends the completion status of the Task in front.
     *
     * @return String representation of Task.
     */
    public String toString() {
        String var10000 = this.getStatusIcon();
        return "[" + var10000 + "] " + this.description;
    }

    /**
     * Returns a String representation of a Task that can be saved and read in the Data file.
     * Appends the completion status of the Task in front.
     *
     * @return String representation of Task.
     */
    public String toSave() {
        String toReturn = this.getStatusIcon();
        return "[" + toReturn + "] " + this.description;
    }
}
