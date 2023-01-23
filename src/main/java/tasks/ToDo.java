package tasks;

/**
 * Task class actually used in Duke.
 */
public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatus() + "] " + super.toString().strip();
    }

    @Override
    public String toSaveString() {
        return "T" + "=" + super.getStatus() + "=" + super.toSaveString().strip();
    }
}
