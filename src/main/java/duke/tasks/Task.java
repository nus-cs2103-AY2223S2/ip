package duke.tasks;

/**
 * Encapsulation of user tasks
 */
public abstract class Task {
    protected String type;
    protected String taskName;
    protected boolean isDone;

    protected Task(String taskName, String type, boolean isDone) {
        this.taskName = taskName;
        this.type = type;
        this.isDone = isDone;
    }

    protected abstract String stringFields();

    /**
     * Marks this task as completed.
     * @return This task
     */
    public Task markDone() {
        this.isDone = true;
        return this;
    }

    /**
     * Marks this task as incomplete.
     * @return This task
     */
    public Task unmarkDone() {
        this.isDone = false;
        return this;
    }

    public boolean getIsDone() {
        return this.isDone;
    }


    /**
     * Represent this task as a string
     * @return Task as string
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.type, this.isDone ? "x" : " ", this.taskName + this.stringFields());
    }
}
