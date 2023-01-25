package berry.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    public String interpretTaskToString() {
        return "T | " + this.getStatusIcon() + " | " + this.description;
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}