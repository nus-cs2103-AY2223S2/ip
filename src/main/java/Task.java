public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[] "); // mark done task with X
    }

    public void markAsDone() {
        System.out.println("Nice! I've marked this task as done:");
        this.isDone = true;
        System.out.println(this);
    }

    public void markAsNotDone() {
        System.out.println("OK, I've marked this task as not done yet:");
        this.isDone = false;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }
}
