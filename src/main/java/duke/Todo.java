package duke;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, String status) {
        super(description, status);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toData() {
        return "T|" + super.toData();
    }

}
