package duke;

// class To do - Type of task with just a description
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toSavedString() {
        String savedString = "T | " + super.toSavedString();
        return savedString;
    }

    @Override
    public String toString() {
        String outputString = "[T]" + super.toString();
        return outputString;
    }
}
