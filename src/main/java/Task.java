public class Task {
    //name of the task.
    private String name;

    //track whether the class is marked as done.
    private boolean isDone = false;

    /**
     * Constructor to instantiate a class.
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
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
        String toPrint = String.format("[%s] %s", status, this.name);
        return toPrint;
    }
}
