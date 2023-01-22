package duke.tasks;

public class Todo extends ITask {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toSaveFormat() {
        return "[T] " + " /content: " + super.toString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
