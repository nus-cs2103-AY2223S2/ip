package items;

/**
 * An abstract class the represents the types of tasks that can be added to the list
 * @author clydelhui
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    /**
     * Creates a <code>Task</code> with the given description and type of task
     * @param description The description of the <code>Task</code>
     * @param taskType A string representing the type of <code>Task</code>
     *                 it is included in the String representation of the <code>Task</code>
     */
    public Task(String description, String taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Creates a <code>Task</code> with the given description, status and type of task
     * @param description The description of the <code>Task</code>
     * @param isDone A boolean indicating if the <code>Task</code> is done
     * @param taskType A string representing the type of <code>Task</code>
     *                 it is included in the String representation of the <code>Task</code>
     */
    public Task(String description, String taskType, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTaskType() {
        return this.taskType;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public abstract String generateStorageForm();

    @Override
    public abstract String toString();

}
