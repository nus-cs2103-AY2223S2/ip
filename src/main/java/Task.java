abstract class Task {
    protected final String description;
    protected final boolean isDone;
    protected final String taskType;

    /*
        default constructor for Task
        this should not be used by the client
     */
    protected Task(String description, boolean isDone, String taskType) {
        this.description = description;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    public String getStatusCheckbox() {
        return (this.isDone) ? "[X]" : "[]";
    }

    public String getTaskTypeBox() {
        return String.format("[%s]", this.taskType);
    }

    // returns new Task that is marked as done
    abstract public Task markTask() throws DukeException;

    // returns new Task that is marked as undone
    abstract public Task unmarkTask() throws DukeException;

    @Override
    public String toString() {
        return this.description;
    }
}
