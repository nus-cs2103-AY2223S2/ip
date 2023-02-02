package duke;

/** Represents Deadline object with
 * a task name string and date to complete.
 */
public class Deadline extends Task {
    private String by;

    /**
     * Creates a deadline with the given task description and date to complete.
     * @param taskName The name of the task.
     * @param by The date to complete the task by.
     */
    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
