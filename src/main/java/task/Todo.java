package task;

/**
 * Todo class that extends from the Task class.
 * It has no additional attribute.
 */
public class Todo extends Task {
    public Todo(String name) {
        super(name.substring("todo ".length()));
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
