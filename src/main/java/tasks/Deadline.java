package tasks;

import java.time.LocalDateTime;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime endDateTime;

    /**
     * Constructs a task with a deadline.
     *
     * @param description Description of the task.
     * @param priority Priority of the task.
     * @param endDateTime Deadline of the task.
     */
    public Deadline(String description, int priority, LocalDateTime endDateTime) {
        super(description, priority);
        this.endDateTime = endDateTime;
    }

    @Override
    public String toEncodedString() {
        return "[" + TaskType.D + "]" + super.toString() + " /by "
            + this.endDateTime.format(Task.getOutputDateTimeFormatter());
    }

    @Override
    public String toString() {
        return "[" + TaskType.D + "]" + super.toString() + " (by: "
                + this.endDateTime.format(Task.getOutputDateTimeFormatter()) + ")";
    }
}
