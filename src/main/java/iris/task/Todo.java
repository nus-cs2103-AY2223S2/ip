package iris.task;

/**
 * Represents a task to be done
 */
public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String storageFormat() {
        return String.join("|", "T", super.storageFormat()) + "\n";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
