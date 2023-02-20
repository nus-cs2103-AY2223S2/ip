package duke.model;

import java.time.LocalDateTime;

import duke.command.utils.DateTimeStringParser;

/**
 * The Deadline class represents a deadline, a specific date and time that a task must be completed by.
 * it extends the Task class and adds the deadline attribute.
 * @author jayanth
 */
public class Deadline extends Task implements Comparable<Deadline> {
    private final LocalDateTime deadline;
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

    @Override
    public int compareTo(Deadline other) {
        if (other.deadline.isBefore(deadline)) {
            return -1;
        } else if (other.deadline.isEqual(deadline)) {
            return 0;
        } else {
            return 1;
        }
    }
}
