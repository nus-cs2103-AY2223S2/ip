package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon for the current task. ("X" or " ")
     * @return String representing the status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Getter function to return the description of the task
     * @return A String representing the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter function to mark the task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Setter function to mark the as undone
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * toString function to represent the task as a string
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }

}