package reborn.data.task;

/**
 * Represents a to-do task.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * String representation of task for storage.
     *
     * @return String of task to store.
     */
    @Override
    public String storageStr() {
        return "T | " + super.getStatusValue() + " | " + super.description;
    }

    /**
     * String representation of task.
     *
     * @return String of task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
