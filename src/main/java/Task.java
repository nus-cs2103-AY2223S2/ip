public abstract class Task {
    protected boolean isDone = false;
    protected String taskDescription;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + taskDescription;
        }
        return "[] " + this.taskDescription;
    }


}
