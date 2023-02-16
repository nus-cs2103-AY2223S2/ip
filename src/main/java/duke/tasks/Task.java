package duke.tasks;

public class Task {
    protected static int counter = 0;
    protected String description;
    protected boolean isDone;


    /**
     * Initialises the object
     *
     * @param description the entire description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        counter += 1;
    }

    /**
     * Gets the type of the task
     *
     * @return a string "task"
     */
    public String getType() {
        return "task";
    }


    /**
     * Gets the status of the task
     *
     * @return a String representing whether task is done
     */
    public String getStatusIcon() {
        // mark done task with X
        if (isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    /**
     * Marks the task as finished
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as unfinished
     */
    public void markAsUnDone() {
        this.isDone = false;
    }

    /**
     * Gets the type of task
     *
     * @return a String representing event detail
     */
    public String getDetail() {
        return this.description;
    }

    /**
     * Outputs the type of the task
     *
     * @return a string of task details
     */
    @Override
    public String toString() {
        return this.description;
    }

    /**
     * Gets the time of task
     *
     * @return a String representing time
     */
    public String getTime() {
        return "";
    }

}
