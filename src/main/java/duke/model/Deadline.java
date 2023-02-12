package duke.model;

import java.time.LocalDateTime;

import duke.command.utils.DateTimeStringParser;

/**
 * The Deadline class represents a deadline, a specific date and time that a task must be completed by.
 * it extends the Task class and adds the deadline attribute.
 * @author jayanth
 */
public class Deadline extends Task {
    private LocalDateTime deadline;
    protected Deadline(String taskDescription, LocalDateTime deadline) {
        super(taskDescription);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeStringParser.DISPLAY_FORMAT) + ")";
    }

    @Override
    boolean isDueOn(LocalDateTime time) {
        return deadline.toLocalDate().isEqual(time.toLocalDate())
                && deadline.toLocalTime().isBefore(time.toLocalTime());
    }
}
