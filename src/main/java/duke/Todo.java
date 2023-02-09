package duke;

// class To do - Type of task with just a description
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toSavedString() {
        return "T | " + super.toSavedString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
