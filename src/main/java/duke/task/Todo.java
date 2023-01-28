package duke.task;

public class Todo extends Task {

    public Todo(String activity) {
        super(activity);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
