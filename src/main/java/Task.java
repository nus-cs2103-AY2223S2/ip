public class Task {
    protected String description;
    protected boolean isDone;

    public static int counter = 0;


    /**
     * Constructor
     * @param description the entire description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        counter += 1;
    }

    /**
     * Decrease the counter by 1
     */
    public static void decreaseCounter() {
        counter -= 1;
    }

    /**
     * Get the type of the task
     * @return a string "task"
     */
    public String getType() {
        return "task";
    }

    /**
     * Method to know the status of the task
     * @return a String representing whether task is done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Method to get the details of the task
     * @return a string description of the task
     */
    public String getDescription() {
        return this.description; // mark done task with X
    }

    /**
     * Mark the task as finished
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as unfinished
     */
    public void markAsUnDone() {
        this.isDone = false;
    }

    /**
     * String representation of the type of the task
     * @return a string of task details
     */
    @Override
    public String toString() {
        return this.description;
    }
}
