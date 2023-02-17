package tasks;

/**
 * Represents task that can be inserted to the program
 */
public class Task {
    private static String UNMARKED = "[ ]";
    private static String MARKED = "[X]";
    private boolean isMarked = false;
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Marking the task as done
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * Un-Marking the task as not yet done
     */
    public void unmark() {
        this.isMarked = false;
    }

    /**
     * String representation of the task
     *
     * @return task string representation
     */
    @Override
    public String toString() {
        if (isMarked) {
            return MARKED + " " + taskName;
        } else {
            return UNMARKED + " " + taskName;
        }
    }
}
