package duke.tasks;
/**
 * A class that represents a task
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task class
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Another constructor for the class
     * @param description the description of the task
     * @param isDone whether the task should be marked upon creation
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * sets the isDone status of the Task
     *
     * @param status the new status of isDone
     */
    public void setDone(Boolean status) {
        this.isDone = status;
    }

    /**
     * return the string representation of Task
     *
     * @return string representation of Task
     */
    @Override
    public String toString() {
        String icon = this.isDone ? "[X]" : "[ ]";
        return icon + " " + description;
    }

    public abstract String toDataFormatString();
}
