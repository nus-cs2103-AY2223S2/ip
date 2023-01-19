public class Task {
    // Attributes:
    protected boolean completed = false;
    protected String title;

    // Constructor:
    public Task(boolean completed, String title) {
        this.completed = completed;
        this.title = title;
    }

    // Methods:
    public String printTask() {
        if (this.completed) {
            return "[X] " + title;
        }
        return "[ ] " + title;
    }

    public void setCompleted(boolean setting) {
        this.completed = setting;
        if (setting) {
            System.out.println("Nice! I've marked this task as completed:");
            System.out.println(" " + this.printTask() + "\n");
        }
        else {
            System.out.println("OK, I've marked this task as incomplete:");
            System.out.println(" " + this.printTask() + "\n");
        }
    }
}
