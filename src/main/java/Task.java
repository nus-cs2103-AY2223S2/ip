/**
 * Represents a task.
 *
 * @author wz2k
 */
public class Task {
    /**
     * The task description
     */
    private String description;

    /**
     * Marking to show if task is done
     */
    private boolean marked;

    /**
     * Constructor for class Task
     * @param desc description of the task
     */
    public Task(String desc) {
        this.description = desc;
        this.marked = false;
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
    public String getTaskDetails() {
        return "[" + this.isMarkedSymbol() + "] " + this.description;
    }

    /**
     * This method marks the task as done
     */
    public void mark() {
        this.marked = true;
    }

    /**
     * This method marks the task as not done
     */
    public void unmark() {
        this.marked = false;
    }
}
