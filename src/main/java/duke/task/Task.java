package duke.task;

/**
 * Represents a task of different types.
 */
public class Task {
    private String name;
    private boolean isDone;

    /**
     * Initialises the task with the task name.
     *
     * @param name Name of the task.
     */
    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Generate the name of the task.
     *
     * @return name Name of the task.
     */
    public String getName() {
        return this.name;
    }

    public boolean nameContainsKeyword(String keyword) {
        return name.contains(keyword);
    }

    /**
     * Generates a string to save the task information on the storage file.
     *
     * @return String representation of the task for the storage file.
     */
    public String toSaveString() {
        return name + "$$$" + (isDone ? "T" : "F");
    }

    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    public String toString() {
        return (isDone ? "[X]" : "[ ]") + " " + this.name;
    }
}
