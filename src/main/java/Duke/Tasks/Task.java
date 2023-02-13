package Duke.Tasks;

public class Task {
    protected String description;
    protected boolean isComplete;

    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    /**
     * Mark the task as completed.
     */
    public void markDone() {
        this.isComplete = true;
    }

    /**
     * Mark the task as incomplete.
     */
    public void markNotDone() {
        this.isComplete = false;
    }

    /**
     * Get the status of the task, whether is it completed?
     * @return If done return "X", else not done return " ".
     */
    public String getTaskStatus() {
        return isComplete ? "X" : " ";
    }

    /**
     * Print the task into the specific file.
     *
     * @return Return the information of the task.
     */
    public String printTask() {
        return String.format("NA | %d | %s ", isComplete ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return "[" + this.getTaskStatus() + "] " + this.description;
    }
}
