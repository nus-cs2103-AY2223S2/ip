/**
 * The Todo task.
 * Inherits from the superclass Task.
 */
public class Todo extends Task {

    public Todo(String nameOfTask, boolean isDone) {
        super(nameOfTask, isDone);
    }

    @Override
    public String getStatus() {
        return "[T]" + super.getStatus();
    }
}
