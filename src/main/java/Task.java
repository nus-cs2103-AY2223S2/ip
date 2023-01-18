/**
 * This class represents a Task that can be kept track of.
 *
 * @version CS2103T AY22/23 Sem 2 Individual Project
 * @author A0233828Y Eugene Tang
 */
public abstract class Task {
    protected String taskName;
    //Status of the task
    protected boolean isDone = false;

    /**
     * Constructs a user task.
     * @param taskName The name of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Sets status of task to be done
     */
    public void setDoneStatus() {
        isDone = true;
    }

    /**
     * Sets status of task to be undone
     */
    public void setUndoneStatus() {
        isDone = false;
    }

    /**
     * Gets the status of the task with the task name.
     * @return a String indicating the type and status of the task.
     */
    public abstract String getStatusOfTaskInString();
}
