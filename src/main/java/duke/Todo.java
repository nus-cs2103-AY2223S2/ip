package duke;

public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
