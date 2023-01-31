package duke;

/**
 * Represents a Task to be done.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides the default Object::toString.
     * @return String representation of a Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + " " + description;
    }
}
