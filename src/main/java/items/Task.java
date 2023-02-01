package items;
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description, String taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public Task(String description, String taskType, boolean done) {
        this.description = description;
        this.isDone = done;
        this.taskType = taskType;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTaskType(){ return this.taskType;}

    public void setDone(){this.isDone = true;}

    public void setNotDone(){this.isDone = false;}

    public String getDescription(){return this.description;}

    public abstract String generateStorageForm();

    @Override
    public abstract String toString();

}
