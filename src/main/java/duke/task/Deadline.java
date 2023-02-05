package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.enums.TaskType;

/**
 * Represents a deadline task.
 *
 * @author wz2k
 */
public class Deadline extends Task {
    /** Deadline of the task */
    private LocalDate by;

    /**
     * Creates a deadline task.
     *
     * @param description Description of the deadline task.
     * @param isMarked Task marking.
     * @param by Task deadline.
     */
    public Deadline(String description, boolean isMarked, String by) {
        super(description, isMarked, TaskType.DEADLINE);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns the task type, checkbox, description and deadline.
     *
     * @return Deadline task details.
     */
    @Override
    public String toString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(pattern) + ")";
    }

    /**
     * Returns the string to store the deadline task in a file.
     *
     * @return File storage version of the deadline task.
     */
    @Override
    public String toTaskStorageString() {
        return "D" + "|" + super.toTaskStorageString() + "|" + by;
    }

    /**
     * Returns if the deadline is due today.
     *
     * @return True is the deadline is due today and false otherwise.
     */
    public boolean isDueToday() {
        LocalDate todayDate = LocalDate.now();
        return by.equals(todayDate);
    }
}
