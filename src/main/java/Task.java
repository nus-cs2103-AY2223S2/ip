/**
 * Represents a task.
 *
 * @author wz2k
 */
public abstract class Task {
    /**
     * The task description.
     */
    private String description;

    /**
     * Marking to show if task is done.
     */
    private boolean marked;

    /**
     * Constructor for Task class.
     *
     * @param desc description of the task.
     */
    public Task(String desc, boolean marked) {
        this.description = desc;
        this.marked = marked;
    }

    /**
     * This method returns the symbol to show if the task is done or not.
     *
     * @return task marked symbol.
     */
    private String isMarkedSymbol() {
        return this.marked ? "X" : " ";
    }

    /**
     * This method returns the task checkbox and description.
     *
     * @return task details.
     */
    public String toString() {
        return "[" + this.isMarkedSymbol() + "] " + this.description;
    }

    /**
     * This method marks the task as done.
     */
    public void mark() {
        this.marked = true;
    }

    /**
     * This method marks the task as not done.
     */
    public void unmark() {
        this.marked = false;
    }

    public String toTaskStorageString() {
        String marked = String.valueOf(this.marked);
        return marked + "|" + this.description;
    }
}
