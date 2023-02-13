package nemo.task;

/**
 * Represents ToDo task.
 *
 * @author Lian Kok Hai
 */
public class ToDo extends Task {
    /**
     * Constructs ToDo object.
     *
     * @param taskName Name of todo task.
     */
    public ToDo(String taskName) {
        super(taskName);
        this.type = "T";
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String encode() {
        return String.format("%s | %s | %s", this.type, this.isDone, this.taskName);
    }
}
