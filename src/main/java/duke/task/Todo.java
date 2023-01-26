package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description, false, "T");
    }

    @Override
    public String toString() {
        return String.format("%s", super.toString());
    }
}
