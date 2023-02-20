package duke.task;

/**
 * Represents a Task with no due date.
 */
public class Todo extends Task {

    /**
     * Constructs an instance of Todo with specified description.
     *
     * @param description Task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs an instance of Todo with specified task status and description.
     *
     * @param isDone Status of the task (completed or not completed).
     * @param description Task description.
     */
    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toCsv() {
        return "T," + super.toCsv();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return String.format("[T]%s",
                super.toString());
    }

}
