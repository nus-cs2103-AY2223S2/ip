package tasks;

import static tasks.Priority.LOW;
import static tasks.TaskType.DEADLINE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a new Deadline task with the given description,
     * priority and deadline datetime.
     *
     * @param description The description of the task.
     * @param by The deadline datetime of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description, DEADLINE, LOW);
        this.by = by;
    }

    /**
     * Constructs a new Deadline task with the given description and deadline datetime.
     *
     * @param description The description of the task.
     * @param by The deadline datetime of the task.
     * @param priority the priority of the event.
     */
    public Deadline(String description, LocalDate by, Priority priority) {
        super(description, DEADLINE, priority);
        this.by = by;
    }

    /**
     * Constructs a new Deadline task with the given description, completion status, and deadline datetime.
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     * @param by The deadline datetime of the task.
     * @param priority the priority of the task.
     */
    public Deadline(String description, boolean isDone, LocalDate by, Priority priority) {
        super(description, isDone, DEADLINE, priority);
        this.by = by;
    }

    /**
     * Formats the given date string "by" into the specified date format.
     *
     * @return A string representation of the date in format "MMM d yyyy".
     */
    private String formatDate() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[" + type.getValue() + "]"
                + super.toString() + " (by: " + formatDate() + ")";
    }

    @Override
    public String parseToSave() {
        return type.getValue() + " | " + super.parseToSave() + " | " + by;
    }
}
