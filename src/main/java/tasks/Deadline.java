package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a new Deadline task with the given description and deadline datetime.
     *
     * @param description The description of the task.
     * @param by The deadline datetime of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a new Deadline task with the given description, completion status, and deadline datetime.
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     * @param by The deadline datetime of the task.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Formats the given date string "by" into the specified date format.
     *
     * @return A string representation of the date in format "MMM d yyyy".
     */
    private String formatDate() {
        LocalDate byDate = LocalDate.parse(by);
        return byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate() + ")";
    }

    @Override
    public String parseToSave() {
        return "D" + " | " + super.parseToSave() + " | " + by;
    }
}
