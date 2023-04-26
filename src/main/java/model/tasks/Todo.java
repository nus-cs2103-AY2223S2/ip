package model.tasks;

/**
 * Represents a Todo task
 */
public class Todo extends Task {
    private final String icon = "[T]";

    /**
     * Constructor for Todo
     * @param description The description of the Todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for Todo
     * @param description The description of the Todo
     * @param status The status of the Todo
     */
    public Todo(String description, Boolean status) {
        super(description);
        setIsComplete(status);
    }

    /**
     * Returns the string representation of the Todo to be saved
     * @return String
     */
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
