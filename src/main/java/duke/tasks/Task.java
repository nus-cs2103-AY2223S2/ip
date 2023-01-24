package duke.tasks;

public abstract class Task {
    protected String type;
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName, String type, boolean isDone) {
        this.taskName = taskName;
        this.type = type;
        this.isDone = isDone;
    }

    public Task markDone() {
        this.isDone = true;
        return this;
    } 
    public Task unmarkDone() {
        this.isDone = false;
        return this;
    }

    public abstract String stringFields(); 

    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + (this.isDone ? "x" : " ") + "] " + this.taskName + this.stringFields();
    }
}