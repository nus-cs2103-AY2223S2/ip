package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    /** Date of deadline */
    private LocalDateTime deadlineDateTime;

    /**
     * Constructs Deadline class.
     *
     * @param description Description of task.
     * @param deadlineDateTime Deadline of task.
     */
    public Deadline(String description, LocalDateTime deadlineDateTime) {
        super(description);
        this.deadlineDateTime = deadlineDateTime;
    }

    /**
     * Gets deadline of task.
     *
     * @return Deadline of task.
     */
    public LocalDateTime getDeadline() {
        return deadlineDateTime;
    }

    /**
     * Sets deadline of task.
     *
     * @param deadlineDateTime Deadline of task to be set to.
     */
    public void setDeadline(LocalDateTime deadlineDateTime) {
        this.deadlineDateTime = deadlineDateTime;
    }

    /**
     * Checks if deadline is overdue.
     *
     * @return Status of event whether it is overdue.
     */
    public boolean isOverdue() {
        return LocalDateTime.now().isAfter(deadlineDateTime);
    }

    /**
     * Checks if deadline is upcoming.
     *
     * @return Status of event whether it is upcoming.
     */
    public boolean isUpcoming() {
        return LocalDateTime.now().isBefore(deadlineDateTime);
    }

    /**
     * {@inheritDoc}
     *
     * Includes type of task and its deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String deadline = this.deadlineDateTime.format(formatter);
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
