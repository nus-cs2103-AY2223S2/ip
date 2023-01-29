package duke.task;


/**
 * Class of Todo which creates the task.
 */
public class Todo extends Task {
    public Todo(String activity) {
        super(activity);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
