package duke.task;

/**
 * Represents a to-do task that the user has.
 * A to-do has no associated dates where it has
 * to be completed by.
 */
public class Todo extends Task{

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
