package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getOutputFormat() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}