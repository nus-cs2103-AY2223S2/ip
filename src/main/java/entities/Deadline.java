package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import enums.TaskType;

/**
 * Represents the Deadline task.
 * Can specify the deadline for a task.
 */
public class Deadline extends Task {
    protected LocalDate by;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Initialize the Deadline task.
     *
     * @param description The description of the task.
     * @param by The deadline for the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.taskType = TaskType.DEADLINE;
    }

    /**
     * Overloaded constructor that takes in a string representation instead.
     *
     * @param description The description of the task.
     * @param by The deadline for the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
        this.taskType = TaskType.DEADLINE;
    }

    /**
     * Serialize the task.
     *
     * @return Returns serialized representation.
     */
    public SerializableTask serialize() {
        String flags = by.toString();
        return new SerializableTask(TaskType.DEADLINE, isDone, description, flags);
    }

    @Override
    public boolean activeOn(LocalDate date) {
        return by.equals(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.getDayOfWeek().toString().toLowerCase() + ", " + by.format(formatter) + ")";
    }
}
