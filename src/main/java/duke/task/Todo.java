package duke.task;

public class Todo extends Task {
    static final String TODO_SYMBOL = "T";

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns String of the Todo task type, status, and description.
     * <p>
     * TaskType will be "T" for Todo.
     * <p>
     * Status will be either "X" or " " depending on mark or unmarked respectively.
     * <p>
     * @return String in the format: [taskType][status] description
     */
    public String toString() {
        return "[" + TODO_SYMBOL + "][" + super.status + "] " + super.description;
    }
}
