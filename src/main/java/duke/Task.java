package duke;

/**
 * Abstract class representing a Task.
 */
public abstract class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;
    protected PriorityLevel priorityLevel;

    /**
     * Constructor for a Task object.
     * @param description Task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priorityLevel = PriorityLevel.MEDIUM;
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
        return this.isDone + "|" + this.priorityLevel + "|" + this.description;
    }

    /**
     * Returns a string representation of a Task.
     *
     * @return String representation of a Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + "[" + this.priorityLevel + "] " + this.description;
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

    /**
     * Sets the priority of a task.
     *
     * @param priorityLevel New priority level of the task.
     */
    public void setPriority(PriorityLevel priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    @Override
    public int compareTo(Task t) {
        assert t.priorityLevel != null;
        return t.priorityLevel.compareTo(this.priorityLevel);
    }
}
