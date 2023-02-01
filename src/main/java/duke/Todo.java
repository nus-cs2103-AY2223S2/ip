package duke;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getFileRepresentation() {
        return "todo|" + super.getFileRepresentation();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
