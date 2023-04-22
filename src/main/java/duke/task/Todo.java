package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskSymbol() {
        return "T";
    }

    @Override
    public String toString() {
        return "[" + getTaskSymbol() + "]" + super.toString();
    }
}