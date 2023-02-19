package duke;

/**
 * Abstract class representing a Task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task object.
     * @param description Task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Marks a Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a Task as not done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of a Task that can be saved into a file.
     * Subclasses must override this to fulfil the format of the save file.
     *
     * @return A String representation of a Task taht can be saved into a file.
     */
    public String getFileRepresentation() {
        return this.isDone + "|" + this.description;
    };

    /**
     * Returns a string representation of a Task.
     *
     * @return String representation of a Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns true if task description contains a certain keyword.
     *
     * @param keyword Keyword to search for in task description
     * @return true if description contains keyword and false otherwise
     */
    public boolean doesDescriptionContain(String keyword) {
        return this.description.contains(keyword);
    }
}
