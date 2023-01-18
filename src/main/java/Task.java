abstract class Task {
    protected final String description;
    protected final boolean done;
    protected final String taskType;

    /*
        default constructor for Task
        this should not be used by the client
     */
    protected Task(String description, boolean isDone, String taskType) {
        this.description = description;
        this.done = isDone;
        this.taskType = taskType;
    }

    // checking if the Task object is done
    public boolean isDone () {
        return this.done;
    }

    public String getStatusCheckbox() {
        String taskStatus = (this.done) ? "[X]" : "[]";
        return taskStatus;
    }

    public String getTaskTypeBox() {
        return String.format("[%s]", this.taskType);
    }

    // returns new Task that is marked as done
    abstract public Task markTask() throws MarkingException;

    // returns new Task that is marked as undone
    abstract public Task unmarkTask() throws UnmarkingException;

    @Override
    public String toString() {
        return this.description;
    }
}
