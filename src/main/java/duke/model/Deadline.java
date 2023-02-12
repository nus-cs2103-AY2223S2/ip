package duke.model;

import java.time.LocalDateTime;

import duke.command.utils.DateTimeStringParser;
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
