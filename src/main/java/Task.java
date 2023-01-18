//Reference from the partial solution provided on CS2103 module website

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
        System.out.println("Nice! I've marked this task as done:\n");
        statusPrompt();
    }

    public void markAsUndone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:\n");
        statusPrompt();
    }

    private void statusPrompt() {
        System.out.println("[" + this.getStatusIcon() + "]" + this.description);
    }

    public String toString() {
        return this.description;
    }
}
