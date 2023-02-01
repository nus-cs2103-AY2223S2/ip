package duke;

public class Todo extends Task {
    public Todo(String description, String fullDescription) {
        super(description, fullDescription);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}