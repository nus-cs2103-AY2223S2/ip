package storage;

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
}
