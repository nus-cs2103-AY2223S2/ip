package duke;

public class Todo extends Task {
    public Todo(String description, String fullDescription) {
        super(description, fullDescription);
        assert fullDescription.startsWith("todo");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}