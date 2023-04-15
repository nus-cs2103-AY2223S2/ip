package model.tasks;

/**
 * Represents a Todo task
 */
public class Todo extends Task {
    private final String icon = "[T]";

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, Boolean status) {
        super(description);
        setIsComplete(status);
    }

    @Override
    public String savedAs() {
        return String.format("T|%s|%s", getIsComplete(), getTaskDesc());
    }

    /**
     * Returns a string representation of this To-Do task
     * @return String
     */
    @Override
    public String toString() {
        return this.icon + super.toString();
    }
}
