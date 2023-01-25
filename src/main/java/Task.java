public class Task {
    // Attributes:
    protected boolean isCompleted = false;
    protected String title;

    // Constructor:
    public Task(String title) {
        this.isCompleted = false;
        this.title = title;
    }

    public Task(int x) throws DukeException {
        throw new DukeException();
    }

    // Methods:
    public String toString() {
        if (this.isCompleted) {
            return "[X] " + title;
        }
        return "[ ] " + title;
    }

    public void setCompleted(boolean setting) {
        this.isCompleted = setting;
        if (setting) {
            System.out.println("Nice! I've marked this task as completed:");
            System.out.println(" " + this.toString() + "\n");
        }
        else {
            System.out.println("OK, I've marked this task as incomplete:");
            System.out.println(" " + this.toString() + "\n");
        }
    }
}
