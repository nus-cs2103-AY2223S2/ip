package duke.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    /**
     * Returns todo task.
     * @param description description of task.
     */
    public Todo(String description) {
        super(description.trim());
    }

    /**
     * Returns format of task for printing to user.
     * @return string format.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Return format of task for backup.
     * @return string format.
     */
    @Override
    public String toBackup() {
        return "T | " + super.toBackup() + "\n";
    }
}
