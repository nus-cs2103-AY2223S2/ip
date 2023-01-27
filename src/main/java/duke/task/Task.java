package duke.task;

/**
 * Encapsulation of a task.
 */
public class Task {
    //name of the task.
    private String name;

    //track whether the class is marked as done.
    private boolean isDone = false;

    /**
     * Constructor to instantiate a task.
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Constructor to instantiate a task.
     * @param name The name of the task.
     * @param isDone Status of the task.
     */
    public Task(String name, boolean isDone) {
        this(name);
        this.isDone = isDone;
    }

    /**
     * Mark the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Mark the task as undone.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Get a string icon representing the 'isDone' status.
     * @return A string representation of the icon.
     */
    public String getStatus() {
        if (this.isDone) {
            return "X";
        }
        return " ";
    }

    /**
     * Print out the 'isDone' status and the name of task.
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
        String status = this.getStatus();
        return (String.format("[%s] %s", status, this.name));
    }

    /**
     * Format task to be stored in data file.
     * @return Returns a  formatted string representation of this task to be stored.
     */
    public String formatStore() {
        String status = isDone ? "1" : "0";
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
