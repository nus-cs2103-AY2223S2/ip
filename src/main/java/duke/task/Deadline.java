package duke.task;

import java.time.LocalDateTime;

import duke.util.DateTimeUtils;

/**
 * Deadline task representation.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Creates a deadline task with description and due date.
     *
     * @param description Description of the deadline.
     * @param by When the deadline task is due.
     */
    public Deadline(String description, String place, LocalDateTime by) {
        super(description, place, TaskType.DEADLINE);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by.format(DateTimeUtils.DATE_TIME_FORMAT_OUTPUT));
    }
}
