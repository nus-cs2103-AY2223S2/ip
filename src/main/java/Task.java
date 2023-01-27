public abstract class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void updateState() {
        this.isDone = !this.isDone;
    }

    public String getDescription(){
        return this.description;
    }

    @Override
    public String toString() {
        return (this.isDone ? "[X]" : "[ ]") + this.description;
    }

    private String getMark() {
        return this.isDone ? "X" : " ";
    }
    public Boolean isDone(){
        return this.isDone;
    }

    public abstract String toFileString();
}
