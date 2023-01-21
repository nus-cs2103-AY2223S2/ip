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

    public void markasDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
    }

    public void markasUnDone() {
        this.isDone = false;
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(this);
    }
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

    //...
}
