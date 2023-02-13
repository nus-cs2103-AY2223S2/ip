package chad.task;

/**
 * Todo item of the user.
 */
public class Todo extends Task {

    /**
     * Constructor to create new task
     *
     * @param task new duke.task to be added
     */
    public Todo(String task) {
        super(task);
    }

    @Override
    public String getDescription() {
        return this.task;
    }

    @Override
    public String toData() {
        String status = this.isCompleted ? "1" : "0";
        return "T | " + status + " | " + this.task;
    }

    @Override
    public String toString() {
        String statusIcon = this.isCompleted ? "X" : " ";
        return "[T][" + statusIcon + "] " + this.getDescription();
    }
}
