package duke.task;

/**
 * A Task contains a description and a completed status.
 * It is an abstract class with basic functionality and is
 * extended by other classes to implement additional features.
 */
public abstract class Task {
    private boolean isCompleted;
    private String description;

    /**
     * Constructor for Task.
     * @param description Description of Task.
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Returns the status of Task.
     * @return "X" if task is completed and " " otherwise.
     */
    public String getStatusIcon() {
        return this.isCompleted ? "X" : " ";
    }

    /**
     * Sets completion status of Task.
     * @param completed Task status.
     */
    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    /**
     * Generates String representing data stored in Task.
     * @return String containing data in Task.
     */
    public String toSaveFormat() {
        String status = isCompleted ? "1" : "0";
        return status + "||" + description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }


}
