package duke.task;

public class Todo extends Task {
    public Todo (String description) {
        super(description);
    }

    public String toData() {
        return String.format("T | %s | %s", this.getStatusIcon(), this.getDescription());
    }

    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
