/**
 * A class to represent a task.
 * @author mmaimer33
 */
public class Task {
    protected String description;
    private boolean completed;

    // Constructor
    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    /**
     * Gets the representative status mark for the task.
     * @return 'X' if done; ' ' otherwise.
     */
    public String getStatusIcon() {
        return this.completed ? "X" : " ";
    }

    /**
     * Mark task as completed.
     */
    public void complete() {
        this.completed = true;
    }

    /**
     * Mark task as incomplete.
     */
    public void uncomplete() {
        this.completed = false;
    }
}
