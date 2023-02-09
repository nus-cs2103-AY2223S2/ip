package duke.task;

/**
 * Encapsulates the related fields and behavior of a task.
 */
public class Task {
    //name of the task.
    private String name;

    //track whether the class is marked as done.
    private boolean isDone = false;

    /**
     * Instantiates Task with one argument given.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Instantiates a task with two arguments given.
     *
     * @param name The name of the task.
     * @param isDone Status of the task.
     */
    public Task(String name, boolean isDone) {
        this(name);
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markIsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void unmarkIsDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representing the 'isDone' status.
     *
     * @return "X" if isDone is true, " " otherwise.
     */
    public String isDone() {
        if (this.isDone) {
            return "X";
        }
        return " ";
    }

    /**
     * Returns the string representation of the 'isDone' status and the name of this task.
     *
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
        String status = this.isDone();
        return (String.format("[%s] %s", status, this.name));
    }

    /**
     * Returns a formatted string representation of this task for storage.
     *
     * @return A string representation of this task.
     */
    public String formatForStorage() {
        String status = this.isDone ? "1" : "0";
        assert status == "1" || status == "0" : "Status not set properly.";
        return (String.format("%s | %s", status, this.name));
    }

    /**
     * Searches for given keyword string in the name of this task.
     *
     * @param keyword The given keyword string.
     * @return True if name contains keyword, false otherwise.
     */
    public boolean containKeyword(String keyword) {
        String formatName = " " + this.name.toUpperCase() + " ";
        return formatName.contains(keyword.toUpperCase());
    }
}
