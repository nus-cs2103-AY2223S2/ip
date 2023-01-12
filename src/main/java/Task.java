public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n [" + getStatusIcon() + "] " + this.description);
    }

    public void markAsIncomplete() {
        this.isDone = false;
        System.out.println("Ok, I've marked this as not done yet:\n [" + getStatusIcon() + "] " + this.description);
    }
}

