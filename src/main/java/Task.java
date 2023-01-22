public class Task {
    protected boolean isDone;
    protected String description;

    /**
     * Constructor to create a new instance of Task.
     * Tasks created are by default not completed.
     * @param description Title of the task
     */
    protected Task(String description, boolean isDone) {
        this.isDone = isDone;
        this.description = description;
    }

    public Task(String description) {
        this(description, false);
    }

    /**
     * Returns a string that indicates if the task is done or not.
     * @return A string representation of status of this task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Setter method to indicate if the task has been completed.
     * @param isDone Boolean to that indicates if the task has been completed.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String serialise() {
        return String.format("T,%s,%s", isDone ? "Y" : "N", description);
    }

    public static Task deserialise(String data) {
        String[] args = data.split(",");

        boolean taskDone = args[1].equals("Y");
        String taskDesc = args[2];

        return new Task(taskDesc, taskDone);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

}
