package duke;

// class To do - Type of task with just a description
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(boolean isMarked, String content) {
        super(isMarked, content);
    }


    @Override
    public String addDivider() {
        return "T | " + super.toSavedString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
