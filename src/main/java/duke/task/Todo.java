package duke.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    /**
     * A constructor to initialize a Todo.
     *
     * @param desc The description of this Todo.
     */
    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
