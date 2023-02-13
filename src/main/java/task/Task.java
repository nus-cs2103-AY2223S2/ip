package task;

/**
 * Base class for all types of tasks.
 */
public abstract class Task {

    private final String taskName;

    private Boolean isTaskDone;

    private final Boolean isTaskDoneDefault = false;

    private final String dateFormatA = "dd/MM/yyyy";

    private final String dateFormatB = "dd-MM-yyyy";

    /**
     * Constructor for Task.
     *
     * @param taskName Name of task.
     * @param isTaskDone Status of task.
     */
    protected Task(String taskName, Boolean isTaskDone) {
        this.taskName = taskName;
        this.isTaskDone = isTaskDone;
    }

    /**
     * Constructor for Task with default value for isTaskDone
     *
     * @param taskName Name of task.
     */
    protected Task(String taskName) {
        this.taskName = taskName;
        this.isTaskDone = this.isTaskDoneDefault;
    }

    /**
     * Method to write all task into file
     *
     * @return String denoting the new task.
     */
    public abstract String writeToFile();

    /**
     * Mark task as done.
     *
     * @return Task that was marked done.
     */
    public Task markIsDone() {
        this.isTaskDone = true;
        return this;
    }

    /**
     * Mark task as not done.
     *
     * @return Task that was marked not done.
     */
    public Task markNotDone() {
        this.isTaskDone = false;
        return this;
    }

    /**
     * Return task status.
     *
     * @return True if task is done.
     */
    public Boolean isDone() {
        return this.isTaskDone;
    }

    @Override
    public String toString() {
        if (isTaskDone) {
            return "[X] " + this.taskName;
        }
        return "[ ] " + this.taskName;
    }

    public String getName() {
        return this.taskName;
    }

    public String getTaskDetails() {
        return this.toString();
    }

    public String getDateFormatA() {
        return this.dateFormatA;
    }

    public String getDateFormatB() {
        return this.dateFormatB;
    }
}
