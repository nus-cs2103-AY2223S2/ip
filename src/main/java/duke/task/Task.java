package duke.task;

/**
 * A base Task class that has a description and a completed status
 * @author Junyi
 */
public class Task implements Comparable<Task> {
    /* True if task is completed */
    protected boolean isDone;

    /* Description of the task */
    protected String description;

    /**
     * Constructor to create a new instance of duke.task.Task.
     * @param description Title of the task
     * @param isDone True if task is completed.
     */
    protected Task(String description, boolean isDone) {
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Constructor to create a new instance of duke.task.Task.
     * Tasks created are by default not completed.
     * @param description Title of the task
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Returns true if the task description contains the given keyword.
     * @param keyword The mentioned keyword.
     * @return True if task description contains the keyword.
     */
    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
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

    /**
     * Returns a string representation suitable to be saved in local storage.
     * @return Serialised string of this task.
     */
    public String serialise() {
        return String.format("T,%s,%s", isDone ? "Y" : "N", description);
    }

    /**
     * Returns an instance of the task represented by the given data.
     * @param data The serialised string of the task.
     * @return An instance of duke.task.Task.
     */
    public static Task deserialise(String data) {
        String[] args = data.split(",");

        boolean taskDone = args[1].equals("Y");
        String taskDesc = args[2];

        return new Task(taskDesc, taskDone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Task task) {
        return 0;
    }
}
