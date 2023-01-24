/**
 * Represents a Todo, which is a type of Task that has no date/time attached to it.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of a Todo.
     *
     * @return  String representation of a Todo in this format: [T][status] description date/time.
     */
    @Override
    public String toString() {
        return String.format("[T][%c] %s", this.getStatusIcon(), this.description);
    }
}
