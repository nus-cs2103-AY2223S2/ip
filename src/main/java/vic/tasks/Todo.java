package vic.tasks;

/**
 * Represents todo task. A <code>Todo</code> class corresponds to
 * the todo task
 */
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
