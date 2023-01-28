package storage;

/**
 * Represents the Todo class
 */
public class Todo extends Task {
    /**
     * Creates a new to do with the specified description
     *
     * @param description The specified description
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toData() {
        return "T | " + super.toData();
    }
}
