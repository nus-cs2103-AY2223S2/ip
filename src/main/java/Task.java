//Reference from the partial solution provided on CS2103 module website

public class Task {
    protected String description;
    protected boolean isDone;
    protected String typeofTask;

    public Task(String description, String typeofTask) {
        this.description = description;
        this.isDone = false;
        this.typeofTask = "";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n");
    }

    public void markAsUndone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:\n");
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}
