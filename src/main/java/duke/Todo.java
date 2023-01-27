package duke;

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

    @Override
    public String toCsv() {
        return "T," + super.toCsv();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
