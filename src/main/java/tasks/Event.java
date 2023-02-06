package tasks;

import java.time.LocalDateTime;

/**
 * Represents a task that takes place within a time period.
 */
public class Event extends Task {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    /**
     * Constructs a task that takes place within a time period.
     *
     * @param description   Description of the task.
     * @param priority Priority of the task.
     * @param startDateTime Starting date and time of the task.
     * @param endDateTime   Ending date and time of the task.
     */
    public Event(String description, int priority, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description, priority);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String toEncodedString() {
        return "[" + TaskType.E + "]" + super.toString() + " /from "
                + this.startDateTime.format(Task.getOutputDateTimeFormatter()) + " /to "
                + this.endDateTime.format(Task.getOutputDateTimeFormatter());
    }

    @Override
    public String toString() {
        return "[" + TaskType.E + "]" + super.toString() + " (from: "
                + this.startDateTime.format(Task.getOutputDateTimeFormatter()) + " to: "
                + this.endDateTime.format(Task.getOutputDateTimeFormatter()) + ")";
    }
}
