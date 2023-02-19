package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo() {
        super();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
