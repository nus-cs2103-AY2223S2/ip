package duke.task;

public class Todo extends Task{
    protected String taskType;

    public Todo(String description) {
        super(description);
        this.taskType = "T";
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
        return "[" + this.taskType + "][" + super.status + "] " + super.description;
    }
}
