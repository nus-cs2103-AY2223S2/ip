package task;

/**
 *
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a to do object with additional parameter
     *
     * @param isDone Boolean on whether the to do object is done.
     * @param description String to accompany and describing the task.
     * @return a To do object.
     */
    public Todo(boolean isDone, String description) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getCommand() {
        return this.isDone
            ? "1 todo " + this.description
            : "0 todo " + this.description;
    }

}
