/**
 * Represents a Task added by the user. It has a description attached to it and a isDone status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks task as done.
     */
    public void markTask() {
        this.isDone = true;
        this.getTask();
    }

    /**
     * Unmarks task as undone.
     */
    public void unmarkTask() {
        this.isDone = false;
        this.getTask();
    }

    /**
     * Marks done task with X.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Gets the name of the task type.
     */
    public char getTaskType() {
        return (this instanceof Todo
                ? 'T'
                : this instanceof Deadline
                ? 'D'
                : 'E');
    }

    /**
     * Gets extra information of the task like its corresponding datetimes.
     */
    public String getExtraInfo() {
        return (this instanceof Deadline
                ? "(by:" + ((Deadline) this).deadline + ")"
                : this instanceof Event
                ? "(from:" + ((Event) this).startDatetime + "to:" + ((Event) this).endDatetime + ")"
                : "");
    }

    /**
     * Prints the task type, status, description, and if relevant, its datetimes.
     */
    public void getTask() {
        System.out.println("[" + this.getTaskType() + "]"
                + "[" + this.getStatusIcon() + "] "
                + this.description
                + this.getExtraInfo());
    }
}
