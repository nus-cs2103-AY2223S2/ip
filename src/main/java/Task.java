public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return this.description;
    }

    public void mark() {
        this.isDone = true;
        System.out.println("Nice! I have marked this task as Done:\n" + "[X] " + this.getDescription() + "\n");
    }

    public void unmark() {
        this.isDone = false;
        System.out.println("Ok! I have marked this task as not done yet:\n" + "[ ] " + this.getDescription() + "\n");
    }
}
