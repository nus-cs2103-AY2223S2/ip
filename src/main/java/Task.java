public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
        System.out.println("Task marked as completed");
        System.out.println(this);
    }

    public void setNotDone() {
        this.isDone = false;
        System.out.println("Task marked as not completed");
        System.out.println(this);
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}