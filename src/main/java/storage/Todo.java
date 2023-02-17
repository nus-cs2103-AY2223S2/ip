package storage;

/**
 * Represents the Todo class
 */
public class Todo extends Task {
    /**
     * Creates a new to do with the specified description
     * @param description The specified description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Custom string to represent a to do for printing
     * @return Custom string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Custom string to represent a to do as data to be saved
     * @return Custom string
     */
    @Override
    public String toData() {
        return "T | " + super.toData();
    }

    @Override
    public int compareTo(Task other) {
        return this.description.compareTo(other.description);
    }
}
